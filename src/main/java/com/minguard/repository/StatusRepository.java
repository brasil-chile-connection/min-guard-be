<<<<<<< Updated upstream
package com.minguard.repository;

import com.minguard.entity.Status;
import com.minguard.enumeration.Statuses;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(Statuses status);

}
=======
package com.minguard.repository;

import com.minguard.entity.Status;
import com.minguard.enumeration.Statuses;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(Statuses status);

}
>>>>>>> Stashed changes
