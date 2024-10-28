package com.minguard.service.impl;

import com.minguard.dto.urgency.UrgencyResponse;
import com.minguard.entity.Urgency;
import com.minguard.mapper.UrgencyMapper;
import com.minguard.repository.UrgencyRepository;
import com.minguard.service.spec.UrgencyService;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UrgencyServiceImpl implements UrgencyService {

    private final UrgencyRepository urgencyRepository;

    @Override
    public Urgency getById(Long urgencyId) {
        return urgencyRepository.findById(urgencyId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find urgency for id=%s", urgencyId)));
    }

    @Override
    public List<UrgencyResponse> findAll() {
        return UrgencyMapper.INSTANCE.toResponses(urgencyRepository.findAll());
    }
}
