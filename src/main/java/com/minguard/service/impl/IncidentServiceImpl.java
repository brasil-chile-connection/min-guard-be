package com.minguard.service.impl;

import com.minguard.dto.incident.IncidentExtendedResponse;
import com.minguard.dto.incident.IncidentResponse;
import com.minguard.dto.incident.RegisterIncidentRequest;
import com.minguard.dto.incident.RegisterIncidentResponse;
import com.minguard.dto.incident.UpdateIncidentRequest;
import com.minguard.entity.Incident;
import com.minguard.entity.IncidentImage;
import com.minguard.entity.Urgency;
import com.minguard.entity.User;
import com.minguard.mapper.IncidentMapper;
import com.minguard.repository.IncidentRepository;
import com.minguard.service.spec.IncidentImageService;
import com.minguard.service.spec.IncidentService;
import com.minguard.service.spec.UrgencyService;
import com.minguard.service.spec.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final UserService userService;
    private final UrgencyService urgencyService;
    private final IncidentImageService incidentImageService;

    @Override
    public List<IncidentExtendedResponse> getAll() {
        return mapToExtendedResponses(incidentRepository.findAll());
    }

    @Override
    public List<IncidentExtendedResponse> getAllIncidentsByReporter(Long reporterId) {
        validateUserAccess(reporterId);
        return mapToExtendedResponses(incidentRepository.findAllByReporter(userService.getUserById(reporterId)));
    }

    @Override
    public List<IncidentExtendedResponse> getAllIncidentsByUrgency(Long urgencyId) {
        Urgency urgency = urgencyService.getById(urgencyId);
        return mapToExtendedResponses(incidentRepository.findAllByUrgency(urgency));
    }

    private List<IncidentExtendedResponse> mapToExtendedResponses(List<Incident> incidents) {
        return incidents.stream().map(this::mapToExtendedResponse).toList();
    }

    private IncidentExtendedResponse mapToExtendedResponse(Incident incident) {
        final var images = incidentImageService.findAllByIncidentId(incident.getId())
                .stream()
                .map(IncidentImage::getUrl).toList();
        return IncidentMapper.INSTANCE.toExtendedResponse(incident, images);
    }

    @Override
    public IncidentExtendedResponse getById(Long incidentId) {
        Incident incident = findIncidentOrThrow(incidentId);
        validateIncidentAccess(incident);
        return mapToExtendedResponse(incident);
    }

    @Transactional
    @Override
    public RegisterIncidentResponse registerIncident(RegisterIncidentRequest request, List<MultipartFile> images) {
        Incident incident = IncidentMapper.INSTANCE.fromRegisterRequest(request);

        incident.setReporter(userService.getAuthenticatedUser());
        assignUrgency(incident, request.getUrgencyId());

        final var response = IncidentMapper.INSTANCE.toRegisterResponse(incidentRepository.save(incident));

        incidentImageService.uploadImages(images, response.id());

        return response;
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
    @Transactional
    public void deleteIncident(Long incidentId) {
        if (!incidentRepository.existsById(incidentId)) {
            throw new EntityNotFoundException("Incident not found");
        }
        incidentImageService.deleteAllByIncidentId(incidentId);
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
