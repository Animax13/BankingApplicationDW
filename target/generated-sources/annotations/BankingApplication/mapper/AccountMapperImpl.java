package BankingApplication.mapper;

import BankingApplication.dto.Account;

import BankingApplication.model.AccountModel;

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2021-05-22T11:31:07+0530",

    comments = "version: 1.1.0.Beta1, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"

)

public class AccountMapperImpl extends AccountMapper {

    @Override

    public AccountModel getAccountModel(Account account) {

        if ( account == null ) {

            return null;
        }

        AccountModel accountModel = new AccountModel();

        accountModel.setAccountNumber( account.getAccountNumber() );

        accountModel.setBalance( account.getBalance() );

        accountModel.setIsActive( account.getIsActive() );

        return accountModel;
    }

    @Override

    public Account getAccount(AccountModel accountModel) {

        if ( accountModel == null ) {

            return null;
        }

        Account account = new Account();

        account.setAccountNumber( accountModel.getAccountNumber() );

        account.setBalance( accountModel.getBalance() );

        account.setIsActive( accountModel.getIsActive() );

        return account;
    }
}

