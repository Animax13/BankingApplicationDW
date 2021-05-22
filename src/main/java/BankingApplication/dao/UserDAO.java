package BankingApplication.dao;

import BankingApplication.model.UserModel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hibernate.criterion.Restrictions.eq;

@Singleton
public class UserDAO extends AbstractDAO<UserModel> {

    private final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Inject
    public UserDAO (SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public boolean addUser (UserModel userModel) {
        boolean success = false;
        try {
            persist(userModel);
            success = true;
        } catch (Exception e) {
            logger.error("UserDAO: Exception while adding user in DB: ", e);
        }
        return success;
    }

    public UserModel getUserByMobileNumber (String mobile) {
        UserModel userModel = null;
        try {
            Criteria criteria = currentSession().createCriteria(UserModel.class);
            criteria.add(eq("mobile", mobile));
            userModel = uniqueResult(criteria);
        } catch (Exception ex) {
            logger.error("UserDAO: Exception in checkIfUserExists of UserDAO: ", ex);
        }
        return userModel;
    }
}
