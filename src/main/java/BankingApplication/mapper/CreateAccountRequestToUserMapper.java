package BankingApplication.mapper;

import BankingApplication.dto.User;
import BankingApplication.dto.request.CreateAccountRequest;
import org.mapstruct.Mapper;

@Mapper(imports = Override.class)
public abstract class CreateAccountRequestToUserMapper {

    public abstract User map (final CreateAccountRequest createAccountRequest);
}
