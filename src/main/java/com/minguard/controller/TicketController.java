package com.minguard.controller;

import com.minguard.dto.ticket.RegisterTicketRequest;
import com.minguard.dto.ticket.RegisterTicketResponse;
import com.minguard.dto.ticket.TicketExtendedResponse;
import com.minguard.dto.ticket.TicketResponse;
import com.minguard.service.spec.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.minguard.dto.ticket.UpdateTicketRequest;

@RequiredArgsConstructor
@Tag(name = "Ticket Controller")
@RequestMapping("/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{ticketId}")
    @Operation(summary = "Get ticket by id", description = "Get details of the ticket by id.")
    public ResponseEntity<TicketExtendedResponse> ticketById(@PathVariable Long ticketId) {
        TicketExtendedResponse response = ticketService.getById(ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new ticket", description = "Creates a new ticket in the system.")
    public ResponseEntity<RegisterTicketResponse> registerTicket(@Valid @RequestBody RegisterTicketRequest request) {
        RegisterTicketResponse registeredTicket = ticketService.registerTicket(request);
        return ResponseEntity.status(HttpStatus.OK).body(registeredTicket);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all tickets", description = "Get details of all tickets. Must have admin role.")
    public ResponseEntity<List<TicketResponse>> allTickets() {
        List<TicketResponse> tickets = ticketService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }

    @PutMapping("/{ticketId}")
    @Operation(summary = "Edit ticket", description = "Updates ticket details by id. Must have admin role.")
    public ResponseEntity<TicketResponse> editIncident(@PathVariable Long ticketId, @Valid @RequestBody UpdateTicketRequest request) {
        TicketResponse updatedTicket = ticketService.editTicket(ticketId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTicket);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{ticketId}")
    @Operation(summary = "Delete ticket", description = "Deletes a ticket by id. Must have admin role.")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
