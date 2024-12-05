<<<<<<< Updated upstream
package com.minguard.service.impl;

import com.minguard.dto.status.StatusResponse;
import com.minguard.enumeration.Statuses;
import com.minguard.mapper.StatusMapper;
import com.minguard.repository.StatusRepository;
import com.minguard.service.spec.StatusService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.minguard.entity.Status;
import jakarta.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public List<StatusResponse> findAll() {
        return StatusMapper.INSTANCE.toResponses(statusRepository.findAll());
    }

    @Override
    public Status getById(Long statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find status for id=%s", statusId)));
    }

    @Override
    public Status getByName(Statuses status) {
        return statusRepository.findByName(status)
                .orElseThrow(() -> new EntityNotFoundException("Can't find status for name=%s" + status));
    }
}
=======
package com.minguard.service.impl;

import com.minguard.dto.status.StatusResponse;
import com.minguard.enumeration.Statuses;
import com.minguard.mapper.StatusMapper;
import com.minguard.repository.StatusRepository;
import com.minguard.service.spec.StatusService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.minguard.entity.Status;
import jakarta.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public List<StatusResponse> findAll() {
        return StatusMapper.INSTANCE.toResponses(statusRepository.findAll());
    }

    @Override
    public Status getById(Long statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find status for id=%s", statusId)));
    }

    @Override
    public Status getByName(Statuses status) {
        return statusRepository.findByName(status)
                .orElseThrow(() -> new EntityNotFoundException("Can't find status for name=%s" + status));
    }
}
>>>>>>> Stashed changes
