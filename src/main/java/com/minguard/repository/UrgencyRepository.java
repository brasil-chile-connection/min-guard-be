package com.minguard.repository;

import com.minguard.entity.Urgency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrgencyRepository extends JpaRepository<Urgency, Long> {

    Optional<Urgency> findById(Long id);

}
