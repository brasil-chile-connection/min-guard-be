package com.minguard.service.spec;

import com.minguard.dto.status.StatusResponse;
import java.util.List;

public interface StatusService {

    List<StatusResponse> findAll();

}
