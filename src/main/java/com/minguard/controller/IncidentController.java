package com.minguard.controller;

import com.minguard.dto.incident.IncidentResponse;
import com.minguard.dto.incident.RegisterIncidentResponse;
import com.minguard.dto.incident.UpdateIncidentRequest;
import com.minguard.dto.incident.RegisterIncidentRequest;
import com.minguard.entity.Incident;
import com.minguard.entity.User;
import com.minguard.mapper.IncidentMapper;
import com.minguard.service.spec.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Tag(name = "Incident Controller")
@RequestMapping("/incident")
@RestController
public class IncidentController {

    private final IncidentService incidentService;

    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{incidentId}")
    @Operation(summary = "Get incident by id", description = "Get details of the incident by id.")
    public ResponseEntity<IncidentResponse> incidentById(@PathVariable Long incidentId) {
        Incident incident = incidentService.getIncidentById(incidentId);

        return ResponseEntity.status(HttpStatus.OK).body(IncidentMapper.INSTANCE.toResponse(incident));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all incidents", description = "Get details of all incidents. Must have admin role.")
    public ResponseEntity<List<IncidentResponse>> allIncidents() {
        List<IncidentResponse> incidents = incidentService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(incidents);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/reporter/{reporterId}")
    @Operation(summary = "Get all incidents by reporter", description = "Get details of all incidents by reporter. Must have admin role.")
    public ResponseEntity<List<IncidentResponse>> allIncidentsByReporter(@PathVariable Long reporterId) {
        List<IncidentResponse> incidents = incidentService.getAllIncidentsByReporter(reporterId);
        return ResponseEntity.status(HttpStatus.OK).body(incidents);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/urgency/{urgencyId}")
    @Operation(summary = "Get all incidents by urgency", description = "Get details of all incidents by urgency. Must have admin role.")
    public ResponseEntity<List<IncidentResponse>> allIncidentsByUrgency(@PathVariable Long urgencyId) {
        List<IncidentResponse> incidents = incidentService.getAllIncidentsByUrgency(urgencyId);
        return ResponseEntity.status(HttpStatus.OK).body(incidents);
    }

   
    @PostMapping("/register")
    @Operation(summary = "Create a new incident", description = "Creates a new incident in the system.")
    public ResponseEntity<RegisterIncidentResponse> register(@Valid @RequestBody RegisterIncidentRequest request) {

        RegisterIncidentResponse registeredIncident = incidentService.register(request);

        return ResponseEntity.ok(registeredIncident);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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
