package com.minguard.service.spec;

import com.minguard.entity.Role;
import com.minguard.util.Roles;

public interface RoleService {

    Role getByName(Roles role);

}
