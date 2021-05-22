package BankingApplication.service;

import BankingApplication.dao.AccountDAO;
import BankingApplication.dao.TransactionDAO;
import BankingApplication.dto.Account;
import BankingApplication.dto.Transaction;
import BankingApplication.dto.request.AddOrWithdrawAccountRequest;
import BankingApplication.dto.response.ServiceResponse;
import BankingApplication.enums.Status;
import BankingApplication.enums.TransactionType;
import BankingApplication.exceptions.ApplicationException;
import BankingApplication.mapper.AccountMapper;
import BankingApplication.mapper.TransactionMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static BankingApplication.BankingApplicationHelper.createUnknownErrorServiceResponse;
import static BankingApplication.BankingApplicationHelper.validateAccount;
import static BankingApplication.constant.ExceptionMessageConstant.BALANCE_NOT_UPDATED_ERROR_MESSAGE;
import static BankingApplication.constant.ExceptionMessageConstant.INSUFFICIENT_FUNDS_ERROR_MESSAGE;
import static BankingApplication.constant.ServiceResponseConstant.SUCCESSFUL_UPDATE_FUND_MESSAGE;

@Singleton
public class TransactionService {

    protected final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    @Inject
    private TransactionService (AccountDAO accountDAO,
                                TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
        accountMapper = Mappers.getMapper(AccountMapper.class);
        transactionMapper = Mappers.getMapper(TransactionMapper.class);
    }

    public ServiceResponse addFund (AddOrWithdrawAccountRequest request) {
        ServiceResponse response = null;
        try {
            Account account = accountMapper.getAccount(accountDAO.getAccountFromAccountNumber(request.getAccountNumber()));
            validateAccount(request, account);
            account.setBalance(account.getBalance() + request.getAmount());
            boolean isBalanceSuccessfullyUpdated = accountDAO.updateAccount(accountMapper.getAccountModel(account));
            if (isBalanceSuccessfullyUpdated) {
                Transaction transaction = createTransaction(account, TransactionType.DEPOSIT, request.getAmount());
                boolean isTransactionMarked = transactionDAO.addTransaction(transactionMapper.getTransactionModel(transaction));
                if (!isTransactionMarked)
                    logger.error("TransactionService : DISASTER : Transaction not marked: {}",transaction.toString());
            } else
                throw new ApplicationException(BALANCE_NOT_UPDATED_ERROR_MESSAGE);
            response = new ServiceResponse(Status.SUCCESS, String.format(SUCCESSFUL_UPDATE_FUND_MESSAGE, account.getBalance()));
        } catch (ApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("TransactionService: Exception in addFund: ", ex);
            response = createUnknownErrorServiceResponse();
        }
        return response;
    }

    public ServiceResponse withDrawFund (AddOrWithdrawAccountRequest request) {
        ServiceResponse response = null;
        try {
            Account account = accountMapper.getAccount(accountDAO.getAccountFromAccountNumber(request.getAccountNumber()));
            validateAccount(request, account);
            if (request.getAmount() > account.getBalance())
                throw new ApplicationException(INSUFFICIENT_FUNDS_ERROR_MESSAGE);
            account.setBalance(account.getBalance() - request.getAmount());
            boolean isBalanceSuccessfullyUpdated = accountDAO.updateAccount(accountMapper.getAccountModel(account));
            if (isBalanceSuccessfullyUpdated) {
                Transaction transaction = createTransaction(account, TransactionType.WITHDRAWAL, request.getAmount());
                boolean isTransactionMarked = transactionDAO.addTransaction(transactionMapper.getTransactionModel(transaction));
                if (!isTransactionMarked)
                    logger.error("TransactionService : DISASTER : Transaction not marked: {}", transaction);
            } else
                throw new ApplicationException(BALANCE_NOT_UPDATED_ERROR_MESSAGE);
            response = new ServiceResponse(Status.SUCCESS, String.format(SUCCESSFUL_UPDATE_FUND_MESSAGE, account.getBalance()));
        } catch (ApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("TransactionService: Exception in withDrawFund: ", ex);
            response = createUnknownErrorServiceResponse();
        }
        return response;
    }

    private Transaction createTransaction (Account sourceAccount, TransactionType transactionType, double amount) {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setSourceAccount(sourceAccount);
        transaction.setType(transactionType);
        transaction.setAmount(amount);
        return transaction;
    }
}
