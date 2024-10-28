package com.minguard.mapper;

import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.entity.Urgency;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UrgencyMapper {

    UrgencyMapper INSTANCE = Mappers.getMapper(UrgencyMapper.class);

    List<UrgencyResponse> toResponses(List<Urgency> urgency);

    UrgencyResponse toResponse(Urgency urgency);

}
