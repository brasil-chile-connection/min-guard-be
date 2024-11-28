package com.minguard.service.spec;

import com.minguard.dto.ticket.RegisterTicketRequest;
import com.minguard.dto.ticket.RegisterTicketResponse;
import com.minguard.dto.ticket.TicketExtendedResponse;

public interface TicketService {

    TicketExtendedResponse getById(Long ticketId);

    RegisterTicketResponse registerTicket(RegisterTicketRequest request);

}
