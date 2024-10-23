package com.minguard.mapper;

import com.minguard.dto.gender.GenderResponse;
import com.minguard.dto.user.RegisterUserRequest;
import com.minguard.dto.user.RegisterUserResponse;
import com.minguard.dto.user.UserResponse;
import com.minguard.entity.Gender;
import com.minguard.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenderMapper {

    GenderMapper INSTANCE = Mappers.getMapper(GenderMapper.class);

    List<GenderResponse> toResponses(List<Gender> genders);

}
