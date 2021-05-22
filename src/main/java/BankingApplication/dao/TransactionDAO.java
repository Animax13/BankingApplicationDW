package BankingApplication.dao;

import BankingApplication.model.TransactionModel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class TransactionDAO extends AbstractDAO<TransactionModel> {

    private final Logger logger = LoggerFactory.getLogger(TransactionDAO.class);

    @Inject
    public TransactionDAO (SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public boolean addTransaction (TransactionModel transactionModel) {
        boolean success = false;
        try {
            persist(transactionModel);
            success = true;
        } catch (Exception ex) {
            logger.error("TransactionDAO: Exception in addTransaction: ", ex);
        }
        return success;
    }
}
