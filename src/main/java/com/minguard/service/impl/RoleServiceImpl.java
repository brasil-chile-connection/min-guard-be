package com.minguard.service.impl;

import com.minguard.dto.role.RoleResponse;
import com.minguard.entity.Role;
import com.minguard.mapper.RoleMapper;
import com.minguard.repository.RoleRepository;
import com.minguard.service.spec.RoleService;
import com.minguard.util.Roles;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getByName(Roles role) {
        return roleRepository.findByName(role)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find role for name=%s", role.name())));
    }

    @Override
    public List<RoleResponse> findAll() {
        return RoleMapper.INSTANCE.toResponses(roleRepository.findAll());
    }
}
