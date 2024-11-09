package com.minguard.repository;

import com.minguard.entity.IncidentImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentImageRepository extends JpaRepository<IncidentImage, Long> {

    List<IncidentImage> findAllByIncidentId(Long incidentId);

}
