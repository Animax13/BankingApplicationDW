package BankingApplication.service;

import BankingApplication.dao.AccountDAO;
import BankingApplication.dao.UserDAO;
import BankingApplication.dto.Account;
import BankingApplication.dto.User;
import BankingApplication.dto.request.AccountRequest;
import BankingApplication.dto.request.CreateAccountRequest;
import BankingApplication.dto.response.ServiceResponse;
import BankingApplication.enums.Status;
import BankingApplication.exceptions.ApplicationException;
import BankingApplication.mapper.AccountMapper;
import BankingApplication.mapper.CreateAccountRequestToUserMapper;
import BankingApplication.mapper.UserMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static BankingApplication.BankingApplicationHelper.createUnknownErrorServiceResponse;
import static BankingApplication.BankingApplicationHelper.validateAccount;
import static BankingApplication.constant.ExceptionMessageConstant.*;
import static BankingApplication.constant.ServiceResponseConstant.*;

@Singleton
public class AccountService {

    protected final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final UserDAO userDAO;
    private final AccountDAO accountDAO;

    private final UserMapper userMapper;
    private final AccountMapper accountMapper;
    private final CreateAccountRequestToUserMapper createAccountRequestToUserMapper;

    @Inject
    private AccountService (UserDAO userDAO,
                        AccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        userMapper = Mappers.getMapper(UserMapper.class);
        accountMapper = Mappers.getMapper(AccountMapper.class);
        createAccountRequestToUserMapper = Mappers.getMapper(CreateAccountRequestToUserMapper.class);
    }

    public ServiceResponse createAccount (CreateAccountRequest createAccountRequest) {
        ServiceResponse createAccountResponse = null;
        try {
            User user = createUserIfNotPresent(createAccountRequest);
            String accountNumber = String.valueOf(System.currentTimeMillis());
            Account account = new Account(accountNumber, 0d, true, user);
            boolean isAccountCreated = accountDAO.createAccount(accountMapper.getAccountModel(account));
            if (!isAccountCreated)
                throw new ApplicationException(ACCOUNT_NOT_CREATED_ERROR_MESSAGE);
            createAccountResponse = new ServiceResponse(Status.SUCCESS, String.format(SUCCESSFUL_ACCOUNT_CREATION_MESSAGE, accountNumber));
        } catch (ApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("AccountService: Exception in createAccount: ", ex);
            createAccountResponse = createUnknownErrorServiceResponse();
        }
        return createAccountResponse;
    }

    public ServiceResponse deactivateAccount (AccountRequest deactivateAccountRequest) {
        ServiceResponse deactivateAccountResponse = null;
        try {
            Account account = accountMapper.getAccount(accountDAO.getAccountFromAccountNumber(deactivateAccountRequest.getAccountNumber()));
            validateAccount(deactivateAccountRequest, account);
            account.setIsActive(false);
            boolean isAccountDeactivated = accountDAO.updateAccount(accountMapper.getAccountModel(account));
            if (!isAccountDeactivated)
                throw new ApplicationException(ACCOUNT_NOT_DEACTIVATED_ERROR_MESSAGE);
            deactivateAccountResponse = new ServiceResponse(Status.SUCCESS, String.format(SUCCESSFUL_ACCOUNT_DEACTIVATION_MESSAGE, deactivateAccountRequest.getAccountNumber()));
        } catch (ApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("AccountService: Exception in deactivateAccount: ", ex);
            deactivateAccountResponse = createUnknownErrorServiceResponse();
        }
        return deactivateAccountResponse;
    }

    public ServiceResponse checkBalance (AccountRequest checkBalanceRequest) {
        ServiceResponse checkBalanceResponse = null;
        try {
            Account account = accountMapper.getAccount(accountDAO.getAccountFromAccountNumber(checkBalanceRequest.getAccountNumber()));
            validateAccount(checkBalanceRequest, account);
            checkBalanceResponse = new ServiceResponse(Status.SUCCESS, String.format(SUCCESSFUL_CHECK_BALANCE_MESSAGE, account.getBalance()));
        } catch (ApplicationException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("AccountService: Exception in checkBalance: ", ex);
            checkBalanceResponse = createUnknownErrorServiceResponse();
        }
        return checkBalanceResponse;
    }

    private User createUserIfNotPresent(CreateAccountRequest createAccountRequest) {
        User user = userMapper.getUser(userDAO.getUserByMobileNumber(createAccountRequest.getMobile()));
        if (user == null) {
            user = createAccountRequestToUserMapper.map(createAccountRequest);
            boolean isUserAdded = userDAO.addUser(userMapper.getUserModel(user));
            if (!isUserAdded)
                throw new ApplicationException(USER_NOT_CREATED_ERROR_MESSAGE);
        }
        return user;
    }
}
