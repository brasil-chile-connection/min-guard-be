package com.minguard.mapper;

import com.minguard.dto.urgency.RegisterUrgencyRequest;
import com.minguard.dto.urgency.RegisterUrgencyResponse;
import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.entity.Urgency;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UrgencyMapper {

    UrgencyMapper INSTANCE = Mappers.getMapper(UrgencyMapper.class);

    Urgency fromRegisterRequest(RegisterUrgencyRequest request);

    RegisterUrgencyResponse toRegisterResponse(Urgency urgency);

    List<UrgencyResponse> toResponses(List<Urgency> urgency);

    UrgencyResponse toResponse(Urgency urgency);

}
