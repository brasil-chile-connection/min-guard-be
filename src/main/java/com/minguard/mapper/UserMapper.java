package com.minguard.mapper;

import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;
import com.minguard.dto.user.UserResponse;
import com.minguard.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//TODO: review fields
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "request.genderId", target = "gender.id")
    @Mapping(target = "password", expression = "java(password)")
    User fromRegisterRequest(RegisterUserRequest request, String password);

    RegisterUserResponse toRegisterResponse(User user);

    UserResponse toResponse(User user);

    List<UserResponse> toResponses(List<User> user);

}
