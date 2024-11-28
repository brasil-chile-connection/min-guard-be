package com.minguard.service.impl;

import com.minguard.dto.ticket.RegisterTicketRequest;
import com.minguard.dto.ticket.RegisterTicketResponse;
import com.minguard.dto.ticket.TicketExtendedResponse;
import com.minguard.entity.Ticket;
import com.minguard.mapper.TicketMapper;
import com.minguard.repository.TicketRepository;
import com.minguard.service.spec.TicketService;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public TicketExtendedResponse getById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .map(TicketMapper.INSTANCE::toExtendedResponse)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found for  id=" + ticketId));
    }

    @Override
    public RegisterTicketResponse registerTicket(RegisterTicketRequest request) {
        Ticket ticket = TicketMapper.INSTANCE.fromRegisterRequest(request);

        ticket.setIdentifier(UUID.randomUUID());

        ticket = ticketRepository.save(ticket);

        return new RegisterTicketResponse(ticket.getId());
    }
}
