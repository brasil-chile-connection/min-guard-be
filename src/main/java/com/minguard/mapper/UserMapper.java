package com.minguard.mapper;

import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;
import com.minguard.dto.user.UserResponse;
import com.minguard.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "gender", ignore = true)
    User fromRegisterRequest(RegisterUserRequest request);

    RegisterUserResponse toRegisterResponse(User user);

    UserResponse toResponse(User user);

    List<UserResponse> toResponses(List<User> user);

}
