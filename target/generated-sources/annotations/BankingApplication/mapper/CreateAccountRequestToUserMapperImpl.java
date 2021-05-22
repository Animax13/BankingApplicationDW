package BankingApplication.mapper;

import BankingApplication.dto.User;

import BankingApplication.dto.request.CreateAccountRequest;

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2021-05-22T11:31:07+0530",

    comments = "version: 1.1.0.Beta1, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"

)

public class CreateAccountRequestToUserMapperImpl extends CreateAccountRequestToUserMapper {

    @Override

    public User map(CreateAccountRequest createAccountRequest) {

        if ( createAccountRequest == null ) {

            return null;
        }

        User user = new User();

        user.setMobile( createAccountRequest.getMobile() );

        user.setName( createAccountRequest.getName() );

        if ( createAccountRequest.getAge() != null ) {

            user.setAge( Integer.parseInt( createAccountRequest.getAge() ) );
        }

        user.setEmail( createAccountRequest.getEmail() );

        return user;
    }
}

