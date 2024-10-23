package com.minguard.service.spec;

import com.minguard.dto.gender.GenderResponse;
import com.minguard.entity.Gender;
import java.util.List;

public interface GenderService {

    Gender getById(Long id);

    List<GenderResponse> findAll();
}
