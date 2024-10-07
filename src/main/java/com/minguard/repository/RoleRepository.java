package com.minguard.repository;

import com.minguard.entity.Role;
import com.minguard.util.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);

}
