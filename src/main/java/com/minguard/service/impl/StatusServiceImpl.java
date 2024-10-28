package com.minguard.service.impl;

import com.minguard.dto.status.StatusResponse;
import com.minguard.mapper.StatusMapper;
import com.minguard.repository.StatusRepository;
import com.minguard.service.spec.StatusService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public List<StatusResponse> findAll() {
        return StatusMapper.INSTANCE.toResponses(statusRepository.findAll());
    }
}
