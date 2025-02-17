package com.minguard.service.spec;

import com.minguard.dto.urgency.RegisterUrgencyResponse;
import com.minguard.dto.urgency.RegisterUrgencyRequest;
import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.entity.Urgency;

import java.util.List;

public interface UrgencyService {

    Urgency getById(Long urgencyId);

    List<UrgencyResponse> findAll();

    RegisterUrgencyResponse register(RegisterUrgencyRequest request);

}
