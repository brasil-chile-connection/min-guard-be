package com.minguard.dto.urgency;

import com.minguard.util.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUrgencyRequest {

    @NotBlank(message = "name" + ValidationMessage.MUST_BE_SPECIFIED)
    private String name;

}
