package com.minguard.dto.incident;

import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.dto.user.UserResponse;
import java.time.LocalDateTime;
import java.util.List;

public record IncidentExtendedResponse(

        Long id,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        String description,

        String title,

        String location,

        UrgencyResponse urgency,

        UserResponse reporter,

        List<String> images

) {

}
