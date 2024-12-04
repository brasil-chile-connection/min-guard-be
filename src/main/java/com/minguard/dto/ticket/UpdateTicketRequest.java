package com.minguard.dto.ticket;

import lombok.Data;

@Data
public class UpdateTicketRequest {

    private String description;

    private String title;

    private String location;

    private Long urgencyId;

    private Long incidentId;

    private Long responsibleId;

    private Long statusId;
}
