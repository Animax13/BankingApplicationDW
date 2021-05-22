package BankingApplication.mapper;

import BankingApplication.dto.User;
import BankingApplication.model.UserModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(imports = Override.class)
public abstract class UserMapper {

    public abstract UserModel getUserModel (final User user);

    @InheritInverseConfiguration
    public abstract User getUser (final UserModel userModel);
}
