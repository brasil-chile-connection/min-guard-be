package com.minguard.dto.status;

import com.minguard.enumeration.Statuses;

public record StatusResponse(Long id, Statuses name) {
}
