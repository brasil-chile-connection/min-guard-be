package com.minguard.service.impl;

import com.minguard.dto.incident.IncidentResponse;
import com.minguard.dto.incident.RegisterIncidentRequest;
import com.minguard.dto.incident.RegisterIncidentResponse;
import com.minguard.dto.incident.UpdateIncidentRequest;
import com.minguard.entity.Incident;
import com.minguard.entity.Urgency;
import com.minguard.entity.User;
import com.minguard.mapper.IncidentMapper;
import com.minguard.repository.IncidentRepository;
import com.minguard.service.spec.IncidentService;
import com.minguard.service.spec.UrgencyService;
import com.minguard.service.spec.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;
import jakarta.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final UserService userService;
    private final UrgencyService urgencyService;

    @Override
    public List<IncidentResponse> findAll() {
        return IncidentMapper.INSTANCE.toResponses(incidentRepository.findAll());
    }

    @Override
    public List<IncidentResponse> getAllIncidentsByReporter(Long reporterId) {
        validateUserAccess(reporterId);
        List<Incident> incidents = incidentRepository.findByReporter(userService.getUserById(reporterId));
        return IncidentMapper.INSTANCE.toResponses(incidents);
    }

    @Override
    public List<IncidentResponse> getAllIncidentsByUrgency(Long urgencyId) {
        Urgency urgency = urgencyService.getById(urgencyId);
        List<Incident> incidents = incidentRepository.findByUrgency(urgency);
        return IncidentMapper.INSTANCE.toResponses(incidents);
    }

    @Override
    public Incident getIncidentById(Long incidentId) {
        Incident incident = findIncidentOrThrow(incidentId);
        validateIncidentAccess(incident);
        return incident;
    }

    @Override
    public RegisterIncidentResponse register(RegisterIncidentRequest request) {
        Incident incident = IncidentMapper.INSTANCE.fromRegisterRequest(request);
        User authenticatedUser = userService.getAuthenticatedUser();
        incident.setReporter(authenticatedUser);
        assignUrgency(incident, request.getUrgencyId());
        return IncidentMapper.INSTANCE.toRegisterResponse(incidentRepository.save(incident));
    }

    @Override
    public IncidentResponse editIncident(Long incidentId, UpdateIncidentRequest request) {
        Incident incident = findIncidentOrThrow(incidentId);
        validateIncidentAccess(incident);

        IncidentMapper.INSTANCE.fromUpdateRequest(incident, request);

        if (request.getUrgencyId() != null) {
            assignUrgency(incident, request.getUrgencyId());
        }

        if (request.getReporterId() != null) {
            assignReporter(incident, request.getReporterId());
        }

        return IncidentMapper.INSTANCE.toResponse(incidentRepository.save(incident));
    }

    @Override
    public void deleteIncident(Long incidentId) {
        if (!incidentRepository.existsById(incidentId)) {
            throw new EntityNotFoundException("Incident not found");
        }
        incidentRepository.deleteById(incidentId);
    }

    private Incident findIncidentOrThrow(Long incidentId) {
        return incidentRepository.findById(incidentId)
            .orElseThrow(() -> new EntityNotFoundException("Incident not found for id=" + incidentId));
    }

    private void validateIncidentAccess(Incident incident) {
        User user = userService.getAuthenticatedUser();
        if (!incident.getReporter().getId().equals(user.getId()) && !user.isAdmin()) {
            throw new AccessDeniedException("You are not authorized to access this incident.");
        }
    }

    private void validateUserAccess(Long reporterId) {
        User user = userService.getAuthenticatedUser();
        if (!reporterId.equals(user.getId()) && !user.isAdmin()) {
            throw new AccessDeniedException("You are not authorized to access incidents for this reporter.");
        }
    }

    private void assignReporter(Incident incident, Long reporterId) {
        incident.setReporter(userService.getUserById(reporterId));
    }

    private void assignUrgency(Incident incident, Long urgencyId) {
        incident.setUrgency(urgencyService.getById(urgencyId));
    }
}
