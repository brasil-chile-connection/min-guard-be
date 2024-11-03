package com.minguard.repository;

import com.minguard.entity.Incident;
import com.minguard.entity.Urgency;
import com.minguard.entity.User;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    Optional<Incident> findById(Long id);

    List<Incident> findByUrgency(Urgency urgency);

    List<Incident> findByReporter(User reporter);

}
