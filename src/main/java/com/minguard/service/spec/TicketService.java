package com.minguard.service.spec;

import com.minguard.dto.ticket.RegisterTicketRequest;
import com.minguard.dto.ticket.RegisterTicketResponse;
import com.minguard.dto.ticket.TicketExtendedResponse;
import com.minguard.dto.ticket.TicketResponse;
import com.minguard.dto.ticket.UpdateTicketRequest;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    TicketExtendedResponse getById(Long ticketId);

    RegisterTicketResponse registerTicket(RegisterTicketRequest request);

    List<TicketResponse> getAll();

    TicketResponse editTicket(Long ticketId, UpdateTicketRequest request);

    void deleteTicket(Long ticketId);

    void completeByUuid(UUID identifier, String closureComment);

}
