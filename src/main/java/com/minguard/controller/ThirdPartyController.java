package com.minguard.controller;

import com.minguard.dto.ticket.CompleteTicketRequest;
import com.minguard.dto.ticket.TicketExtendedResponse;
import com.minguard.service.spec.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "3rd Party Controller")
@RequestMapping("/third-party")
@RestController
public class ThirdPartyController {

    private final TicketService ticketService;

    @PatchMapping("/ticket/{identifier}/complete")
    @Operation(summary = "Set ticket as done", description = "Set ticket with given UUID as done and sets the closure comment.")
    public ResponseEntity<Void> completeTicketWithComment(@PathVariable UUID identifier,
                                                          @RequestBody CompleteTicketRequest request) {
        ticketService.completeByIdentifier(identifier, request.getClosureComment());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ticket/{identifier}")
    @Operation(summary = "Get ticket by identifier", description = "Get ticket by UUID identifier.")
    public ResponseEntity<TicketExtendedResponse> getTicket(@PathVariable UUID identifier) {
        return ResponseEntity.ok(ticketService.getByIdentifier(identifier));
    }

}
