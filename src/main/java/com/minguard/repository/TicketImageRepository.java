package com.minguard.repository;

import com.minguard.entity.TicketImage;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketImageRepository extends JpaRepository<TicketImage, Long> {

    List<TicketImage> findAllByTicketId(Long ticketId);

}
