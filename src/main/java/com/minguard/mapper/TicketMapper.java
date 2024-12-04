package com.minguard.mapper;

import com.minguard.dto.ticket.RegisterTicketRequest;
import com.minguard.dto.ticket.TicketExtendedResponse;
import com.minguard.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketExtendedResponse toExtendedResponse(Ticket incident);

    @Mapping(target = "status.id", source = "statusId")
    @Mapping(target = "urgency.id", source = "urgencyId")
    @Mapping(target = "incident.id", source = "incidentId")
    @Mapping(target = "responsible.id", source = "responsibleId")
    Ticket fromRegisterRequest(RegisterTicketRequest request);

}
