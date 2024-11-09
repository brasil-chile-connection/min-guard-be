package com.minguard.controller;

import com.minguard.dto.incident.IncidentExtendedResponse;
import com.minguard.dto.incident.IncidentResponse;
import com.minguard.dto.incident.RegisterIncidentRequest;
import com.minguard.dto.incident.RegisterIncidentResponse;
import com.minguard.dto.incident.UpdateIncidentRequest;
import com.minguard.service.spec.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Tag(name = "Incident Controller")
@RequestMapping("/incident")
@RestController
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping("/{incidentId}")
    @Operation(summary = "Get incident by id", description = "Get details of the incident by id.")
    public ResponseEntity<IncidentExtendedResponse> incidentById(@PathVariable Long incidentId) {
        IncidentExtendedResponse incident = incidentService.getById(incidentId);
        return ResponseEntity.status(HttpStatus.OK).body(incident);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all incidents", description = "Get details of all incidents. Must have admin role.")
    public ResponseEntity<List<IncidentExtendedResponse>> allIncidents() {
        List<IncidentExtendedResponse> incidents = incidentService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(incidents);
    }

    @GetMapping("/reporter/{reporterId}")
    @Operation(summary = "Get all incidents by reporter", description = "Get details of all incidents by reporter. Must have admin role.")
    public ResponseEntity<List<IncidentExtendedResponse>> allIncidentsByReporter(@PathVariable Long reporterId) {
        List<IncidentExtendedResponse> incidents = incidentService.getAllIncidentsByReporter(reporterId);
        return ResponseEntity.status(HttpStatus.OK).body(incidents);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/urgency/{urgencyId}")
    @Operation(summary = "Get all incidents by urgency", description = "Get details of all incidents by urgency. Must have admin role.")
    public ResponseEntity<List<IncidentExtendedResponse>> allIncidentsByUrgency(@PathVariable Long urgencyId) {
        List<IncidentExtendedResponse> incidents = incidentService.getAllIncidentsByUrgency(urgencyId);
        return ResponseEntity.status(HttpStatus.OK).body(incidents);
    }

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    @Operation(summary = "Create a new incident", description = "Creates a new incident in the system.")
    public ResponseEntity<RegisterIncidentResponse> register(@RequestPart("request") @Valid RegisterIncidentRequest request,
                                                             @RequestPart("images") List<MultipartFile> images) {
        RegisterIncidentResponse registeredIncident = incidentService.registerIncident(request, images);
        return ResponseEntity.status(HttpStatus.OK).body(registeredIncident);
    }

    @PutMapping("/{incidentId}")
    @Operation(summary = "Edit incident", description = "Updates incident details by id. Must have admin role.")
    public ResponseEntity<IncidentResponse> editIncident(@PathVariable Long incidentId, @Valid @RequestBody UpdateIncidentRequest request) {
        IncidentResponse updatedIncident = incidentService.editIncident(incidentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedIncident);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{incidentId}")
    @Operation(summary = "Delete incident", description = "Deletes a incident by id. Must have admin role.")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long incidentId) {
        incidentService.deleteIncident(incidentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
