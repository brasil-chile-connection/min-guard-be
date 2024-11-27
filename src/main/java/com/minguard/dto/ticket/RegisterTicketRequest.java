package com.minguard.dto.ticket;

import com.minguard.util.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterTicketRequest {

    private String description;

    @NotBlank(message = "title" + ValidationMessage.MUST_BE_SPECIFIED)
    private String title;

    @NotBlank(message = "location" + ValidationMessage.MUST_BE_SPECIFIED)
    private String location;

    @NotNull(message = "urgencyId" + ValidationMessage.MUST_BE_SPECIFIED)
    private Long urgencyId;

    private Long incidentId;

    private Long responsibleId;

    @NotNull(message = "statusId" + ValidationMessage.MUST_BE_SPECIFIED)
    private Long statusId;

}
