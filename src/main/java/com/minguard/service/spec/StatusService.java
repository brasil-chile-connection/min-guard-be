package com.minguard.service.spec;

import com.minguard.dto.status.StatusResponse;
import java.util.List;
import com.minguard.entity.Status;

public interface StatusService {

    List<StatusResponse> findAll();
    Status getById(Long statusId);

}
