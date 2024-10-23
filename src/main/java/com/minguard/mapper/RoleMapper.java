package com.minguard.mapper;

import com.minguard.dto.role.RoleResponse;
import com.minguard.entity.Role;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    List<RoleResponse> toResponses(List<Role> roles);
}
