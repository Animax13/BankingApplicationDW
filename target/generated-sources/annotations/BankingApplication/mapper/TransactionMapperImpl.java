package BankingApplication.mapper;

import BankingApplication.dto.Transaction;

import BankingApplication.model.TransactionModel;

import javax.annotation.Generated;

import org.mapstruct.factory.Mappers;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2021-05-22T12:34:54+0530",

    comments = "version: 1.1.0.Beta1, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"

)

public class TransactionMapperImpl extends TransactionMapper {

    private final AccountMapper accountMapper = Mappers.getMapper( AccountMapper.class );

    @Override

    public TransactionModel getTransactionModel(Transaction transaction) {

        if ( transaction == null ) {

            return null;
        }

        TransactionModel transactionModel = new TransactionModel();

        transactionModel.setId( transaction.getId() );

        transactionModel.setSourceAccount( accountMapper.getAccountModel( transaction.getSourceAccount() ) );

        transactionModel.setDestinationAccount( accountMapper.getAccountModel( transaction.getDestinationAccount() ) );

        transactionModel.setType( transaction.getType() );

        transactionModel.setAmount( transaction.getAmount() );

        return transactionModel;
    }

    @Override

    public Transaction getTransaction(TransactionModel transactionModel) {

        if ( transactionModel == null ) {

            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setId( transactionModel.getId() );

        transaction.setSourceAccount( accountMapper.getAccount( transactionModel.getSourceAccount() ) );

        transaction.setDestinationAccount( accountMapper.getAccount( transactionModel.getDestinationAccount() ) );

        transaction.setType( transactionModel.getType() );

        transaction.setAmount( transactionModel.getAmount() );

        return transaction;
    }
}

