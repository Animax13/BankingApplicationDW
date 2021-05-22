package BankingApplication.dao;

import BankingApplication.exceptions.ApplicationException;
import BankingApplication.model.AccountModel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static BankingApplication.constant.ExceptionMessageConstant.*;
import static org.hibernate.criterion.Restrictions.eq;

@Singleton
public class AccountDAO extends AbstractDAO<AccountModel> {

    private final Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    @Inject
    public AccountDAO (SessionFactory sessionFactory)  {
        super(sessionFactory);
    }

    public AccountModel getAccountFromAccountNumber (String accountNumber) {
        AccountModel accountModel = null;
        try {
            Criteria criteria = currentSession().createCriteria(AccountModel.class);
            criteria.add(eq("accountNumber", accountNumber));
            accountModel = uniqueResult(criteria);
        } catch (Exception e) {
            logger.error("AccountDAO: Exception in ");
        }
        return accountModel;
    }

    public boolean createAccount (AccountModel accountModel){
        boolean success = false;
        try {
            AccountModel account = getAccountFromAccountNumber(accountModel.getAccountNumber());
            if (account != null){
                throw new ApplicationException(ACCOUNT_ALREADY_EXISTS_ERROR_MESSAGE);
            }
            persist(accountModel);
            success = true;
        } catch (ApplicationException ex) {
            throw ex;
        } catch (Exception ex){
            logger.error("AccountDAO: Exception in createAccount: ", ex);
        }
        return success;
    }

    public boolean updateAccount (AccountModel accountModel) {
        boolean success = false;
        try {
            persist(accountModel);
            success = true;
        } catch (Exception ex) {
            logger.error("AccountDAO: Exception in updateAccount: ", ex);
        }
        return success;
    }
}
