package com.minguard.mapper;

import com.minguard.dto.ticket.RegisterTicketRequest;
import com.minguard.dto.ticket.TicketExtendedResponse;
import com.minguard.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketExtendedResponse toExtendedResponse(Ticket incident);

    Ticket fromRegisterRequest(RegisterTicketRequest request);

}
