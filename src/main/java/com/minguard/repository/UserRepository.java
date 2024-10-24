package com.minguard.repository;

import com.minguard.entity.User;
import com.minguard.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

    Optional<User> findById(Long id);
}
