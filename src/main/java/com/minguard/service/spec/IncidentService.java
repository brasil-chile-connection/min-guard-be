package com.minguard.service.spec;

import com.minguard.dto.incident.IncidentExtendedResponse;
import com.minguard.dto.incident.IncidentResponse;
import com.minguard.dto.incident.RegisterIncidentRequest;
import com.minguard.dto.incident.RegisterIncidentResponse;
import com.minguard.dto.incident.UpdateIncidentRequest;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IncidentService {

    List<IncidentExtendedResponse> getAll();

    List<IncidentExtendedResponse> getAllIncidentsByReporter(Long reporterId);

    List<IncidentExtendedResponse> getAllIncidentsByUrgency(Long urgencyId);

    IncidentExtendedResponse getById(Long incidentId);

    RegisterIncidentResponse registerIncident(RegisterIncidentRequest request, List<MultipartFile> images);

    IncidentResponse editIncident(Long incidentId, UpdateIncidentRequest request);

    void deleteIncident(Long incidentId);
}
