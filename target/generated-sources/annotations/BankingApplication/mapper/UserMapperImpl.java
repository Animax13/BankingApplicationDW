package BankingApplication.mapper;

import BankingApplication.dto.User;

import BankingApplication.model.UserModel;

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2021-05-22T11:31:07+0530",

    comments = "version: 1.1.0.Beta1, compiler: javac, environment: Java 1.8.0_271 (Oracle Corporation)"

)

public class UserMapperImpl extends UserMapper {

    @Override

    public UserModel getUserModel(User user) {

        if ( user == null ) {

            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setName( user.getName() );

        userModel.setAge( user.getAge() );

        userModel.setEmail( user.getEmail() );

        userModel.setMobile( user.getMobile() );

        return userModel;
    }

    @Override

    public User getUser(UserModel userModel) {

        if ( userModel == null ) {

            return null;
        }

        User user = new User();

        user.setMobile( userModel.getMobile() );

        user.setName( userModel.getName() );

        user.setAge( userModel.getAge() );

        user.setEmail( userModel.getEmail() );

        return user;
    }
}

