package com.minguard.dto.incident;

import com.minguard.util.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterIncidentRequest {

    private String description;

    @NotBlank(message = "title" + ValidationMessage.MUST_BE_SPECIFIED)
    private String title;

    @NotBlank(message = "location" + ValidationMessage.MUST_BE_SPECIFIED)
    private String location;

    @NotNull(message = "urgencyId" + ValidationMessage.MUST_BE_SPECIFIED)
    private Long urgencyId;

}
