package com.minguard.mapper;

import com.minguard.dto.status.StatusResponse;
import com.minguard.entity.Status;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusMapper {

    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    List<StatusResponse> toResponses(List<Status> statuses);

}
