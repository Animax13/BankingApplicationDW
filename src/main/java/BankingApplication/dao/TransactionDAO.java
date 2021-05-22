package BankingApplication.dao;

import BankingApplication.model.TransactionModel;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionDAO extends AbstractDAO<TransactionModel> {

    private final Logger logger = LoggerFactory.getLogger(TransactionDAO.class);

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
