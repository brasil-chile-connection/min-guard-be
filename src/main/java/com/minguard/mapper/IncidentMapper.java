package com.minguard.mapper;

import com.minguard.dto.incident.IncidentResponse;
import com.minguard.entity.Incident;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidentMapper {

    IncidentMapper INSTANCE = Mappers.getMapper(IncidentMapper.class);

    // @Mapping(target = "password", ignore = true)
    // @Mapping(target = "gender", ignore = true)
    // User fromRegisterRequest(RegisterUserRequest request);

    // RegisterUserResponse toRegisterResponse(User user);

    IncidentResponse toResponse(Incident incident);

    List<IncidentResponse> toResponses(List<Incident> incident);

}
