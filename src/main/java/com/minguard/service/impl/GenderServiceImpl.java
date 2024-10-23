package com.minguard.service.impl;

import com.minguard.dto.gender.GenderResponse;
import com.minguard.entity.Gender;
import com.minguard.mapper.GenderMapper;
import com.minguard.repository.GenderRepository;
import com.minguard.service.spec.GenderService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public Gender getById(Long id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find gender by id=" + id));
    }

    @Override
    public List<GenderResponse> findAll() {
        return GenderMapper.INSTANCE.toResponses(genderRepository.findAll());
    }
}
