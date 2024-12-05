package com.minguard.repository;

import com.minguard.entity.Ticket;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket getByIdentifier(UUID identifier);

}
