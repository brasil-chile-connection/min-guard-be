package com.minguard.mapper;

import com.minguard.dto.gender.GenderResponse;
import com.minguard.dto.role.RoleResponse;
import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;
import com.minguard.dto.user.UserResponse;
import com.minguard.entity.Gender;
import com.minguard.entity.Role;
import com.minguard.entity.User;
import com.minguard.util.Genders;
import com.minguard.util.Roles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T23:12:50-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromRegisterRequest(RegisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( request.email() );
        user.setFirstName( request.firstName() );
        user.setLastName( request.lastName() );
        user.setMobilePrefix( request.mobilePrefix() );
        user.setMobileNumber( request.mobileNumber() );
        user.setAcceptTc( request.acceptTc() );

        return user;
    }

    @Override
    public RegisterUserResponse toRegisterResponse(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;

        id = user.getId();

        RegisterUserResponse registerUserResponse = new RegisterUserResponse( id );

        return registerUserResponse;
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String firstName = null;
        String lastName = null;
        String mobilePrefix = null;
        String mobileNumber = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        Boolean acceptTc = null;
        GenderResponse gender = null;
        RoleResponse role = null;
        String profilePicturePath = null;

        id = user.getId();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        mobilePrefix = user.getMobilePrefix();
        mobileNumber = user.getMobileNumber();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
        acceptTc = user.getAcceptTc();
        gender = genderToGenderResponse( user.getGender() );
        role = roleToRoleResponse( user.getRole() );
        profilePicturePath = user.getProfilePicturePath();

        UserResponse userResponse = new UserResponse( id, email, firstName, lastName, mobilePrefix, mobileNumber, createdAt, updatedAt, acceptTc, gender, role, profilePicturePath );

        return userResponse;
    }

    @Override
    public List<UserResponse> toResponses(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( user.size() );
        for ( User user1 : user ) {
            list.add( toResponse( user1 ) );
        }

        return list;
    }

    protected GenderResponse genderToGenderResponse(Gender gender) {
        if ( gender == null ) {
            return null;
        }

        Long id = null;
        Genders name = null;

        id = gender.getId();
        name = gender.getName();

        GenderResponse genderResponse = new GenderResponse( id, name );

        return genderResponse;
    }

    protected RoleResponse roleToRoleResponse(Role role) {
        if ( role == null ) {
            return null;
        }

        Long id = null;
        Roles name = null;

        id = role.getId();
        name = role.getName();

        RoleResponse roleResponse = new RoleResponse( id, name );

        return roleResponse;
    }
}
