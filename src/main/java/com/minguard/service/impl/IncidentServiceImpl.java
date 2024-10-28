package com.minguard.service.impl;

import com.minguard.dto.incident.IncidentResponse;
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
}
