package BankingApplication.mapper;

import BankingApplication.dto.Account;
import BankingApplication.model.AccountModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(imports = Override.class, uses = UserMapper.class)
public abstract class AccountMapper {

    public abstract AccountModel getAccountModel(final Account account);

    @InheritInverseConfiguration
    public abstract Account getAccount(final AccountModel accountModel);
}
