package com.minguard.dto.ticket;

import com.minguard.dto.incident.IncidentResponse;
import com.minguard.dto.status.StatusResponse;
import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.dto.user.UserResponse;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketResponse(

        Long id,

        IncidentResponse incidentResponse,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String description,

        String title,

        String location,

        UrgencyResponse urgency,

        UserResponse responsible,

        UUID identifier,

        StatusResponse status
) {

}
