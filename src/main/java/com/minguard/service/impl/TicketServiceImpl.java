package com.minguard.service.impl;

import com.minguard.dto.ticket.RegisterTicketRequest;
import com.minguard.dto.ticket.RegisterTicketResponse;
import com.minguard.dto.ticket.TicketExtendedResponse;
import com.minguard.dto.ticket.TicketResponse;
import com.minguard.dto.ticket.UpdateTicketRequest;
import com.minguard.entity.Ticket;
import com.minguard.enumeration.Statuses;
import com.minguard.entity.TicketImage;
import com.minguard.mapper.TicketMapper;
import com.minguard.repository.TicketRepository;
import com.minguard.service.spec.TicketService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.minguard.service.spec.UrgencyService;
import com.minguard.service.spec.StatusService;
import com.minguard.service.spec.TicketImageService;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final StatusService statusService;
    private final UrgencyService urgencyService;
    private final TicketImageService ticketImageService;


    @Override
    public TicketExtendedResponse getById(Long ticketId) {
        Ticket ticket = findTicketOrThrow(ticketId);
        return mapToExtendedResponse(ticket);
    }

    @Transactional
    @Override
    public RegisterTicketResponse registerTicket(RegisterTicketRequest request, List<MultipartFile> images) {
        Ticket ticket = TicketMapper.INSTANCE.fromRegisterRequest(request);
        if (ticket.getResponsible() != null && ticket.getResponsible().getId() == null) {
            ticket.setResponsible(null);
        }
        if (ticket.getIncident() != null && ticket.getIncident().getId() == null) {
            ticket.setIncident(null);
        }

        ticket.setIdentifier(UUID.randomUUID());

        ticket = ticketRepository.save(ticket);
        ticketImageService.uploadImages(images, ticket.getId());

        return new RegisterTicketResponse(ticket.getId());
    }


    @Override
    public List<TicketExtendedResponse> getAll() {
        return mapToExtendedResponses(ticketRepository.findAll());
    }

    @Override
    public TicketResponse editTicket(Long ticketId, UpdateTicketRequest request) {
        Ticket ticket = findTicketOrThrow(ticketId);

        TicketMapper.INSTANCE.fromUpdateRequest(ticket, request);

        if (request.getUrgencyId() != null) {
            assignUrgency(ticket, request.getUrgencyId());
        }

        if (request.getStatusId() != null) {
            assignStatus(ticket, request.getStatusId());
        }
        //@TODO - ver se tem como editar o responsibleId e o incidentId
        ticket.setUpdatedAt(LocalDateTime.now());

        return TicketMapper.INSTANCE.toResponse(ticketRepository.save(ticket));
    }

    @Override
    @Transactional
    public void deleteTicket(Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new EntityNotFoundException("Ticket not found");
        }
        ticketImageService.deleteAllByTicketId(ticketId);
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public void completeByIdentifier(UUID identifier, String closureComment) {
        final var ticket = ticketRepository.getByIdentifier(identifier);

        final var statusesThatAllowClosing = List.of(Statuses.PENDING, Statuses.IN_PROGRESS);
        if (!statusesThatAllowClosing.contains(ticket.getStatus().getName())) {
            throw new IllegalStateException("Ticket can't be set as done because its current status is " + ticket.getStatus().getName());
        }

        ticket.setStatus(statusService.getByName(Statuses.DONE));
        ticket.setClosureComment(closureComment);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    @Override
    public TicketExtendedResponse getByIdentifier(UUID identifier) {
        return mapToExtendedResponse(ticketRepository.getByIdentifier(identifier));
    }

    private Ticket findTicketOrThrow(Long ticketId) {
        return ticketRepository.findById(ticketId)
            .orElseThrow(() -> new EntityNotFoundException("Ticket not found for id=" + ticketId));
    }

    private void assignUrgency(Ticket ticket, Long urgencyId) {
        ticket.setUrgency(urgencyService.getById(urgencyId));
    }

    private void assignStatus(Ticket ticket, Long statusId) {
        ticket.setStatus(statusService.getById(statusId));
    }

    private List<TicketExtendedResponse> mapToExtendedResponses(List<Ticket> tickets) {
        return tickets.stream().map(this::mapToExtendedResponse).toList();
    }

    private TicketExtendedResponse mapToExtendedResponse(Ticket ticket) {
        final var images = ticketImageService.findAllByTicketId(ticket.getId())
                .stream()
                .map(TicketImage::getUrl).toList();
        return TicketMapper.INSTANCE.toExtendedResponse(ticket, images);
    }


}
