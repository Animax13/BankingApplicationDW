package BankingApplication.mapper;

import BankingApplication.dto.Transaction;
import BankingApplication.model.TransactionModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(imports = Override.class, uses = AccountMapper.class)
public abstract class TransactionMapper {

    public abstract TransactionModel getTransactionModel (final Transaction transaction);

    @InheritInverseConfiguration
    public abstract Transaction getTransaction (final TransactionModel transactionModel);
}
