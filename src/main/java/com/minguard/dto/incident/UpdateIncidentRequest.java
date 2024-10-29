package com.minguard.dto.incident;

import com.minguard.util.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateIncidentRequest {

    private String description;

    private String title;

    private String location;

    private Long urgencyId;

    private Long reporterId;
}
