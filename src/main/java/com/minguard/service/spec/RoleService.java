package com.minguard.service.spec;

import com.minguard.dto.role.RoleResponse;
import com.minguard.entity.Role;
import com.minguard.enumeration.Roles;
import java.util.List;

public interface RoleService {

    Role getByName(Roles role);

    List<RoleResponse> findAll();

}
