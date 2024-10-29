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
        User reporter = userService.getUserById(reporterId);

        List<Incident> incidents = incidentRepository.findByReporter(reporter);
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
        return incidentRepository.findById(incidentId)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find incident for id=%s", incidentId)));
    }

    private void assignReporter(Incident incident, Long reporterId) {
        User reporter = userService.getById(reporterId);
        incident.setReporter(reporter);
    }

    private void assignUrgency(Incident incident, Long urgencyId) {
        Urgency urgency = urgencyService.getById(urgencyId);
        incident.setUrgency(urgency);
    }

    @Override
    public RegisterIncidentResponse register(RegisterIncidentRequest request) {

        Incident incident = IncidentMapper.INSTANCE.fromRegisterRequest(request);

        assignReporter(incident, request.getReporterId);
        assignUrgency(incident, request.getUrgencyId);

        return IncidentMapper.INSTANCE.toRegisterResponse(incidentRepository.save(incident));
    }

    @Override
    public IncidentResponse editIncident(Long incidentId, UpdateIncidentRequest request) {

        Incident incident = incidentRepository.findById(incidentId)
            .orElseThrow(() -> new RuntimeException("Incident not found"));

        IncidentMapper.INSTANCE.fromUpdateRequest(incident,request);

        if (request.getUrgencyId() != null) {
            assignUrgency(incident, request.getUrgencyId);
        }

        if (request.getReporterId() != null) {
            assignReporter(incident, request.getReporterId);
        }

        return IncidentMapper.INSTANCE.toResponse(incidentRepository.save(incident));
    }

    @Override
    public void deleteIncident(Long incidentId) {
        if (!incidentRepository.existsById(incidentId)) {
            throw new RuntimeException("Incident not found");
        }

        incidentRepository.deleteById(incidentId);
    }
}
