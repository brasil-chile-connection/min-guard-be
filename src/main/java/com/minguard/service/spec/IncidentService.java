package com.minguard.service.spec;

import com.minguard.dto.incident.IncidentResponse;
import com.minguard.entity.Urgency;
import com.minguard.entity.User;
import com.minguard.entity.Incident;
import java.util.List;

public interface IncidentService {

    List<IncidentResponse> findAll();

    List<IncidentResponse> getAllIncidentsByReporter(Long reporterId);

    List<IncidentResponse> getAllIncidentsByUrgency(Long urgencyId);

    Incident getIncidentById(Long incidentId);
}
