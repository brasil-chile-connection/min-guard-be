package com.minguard.service.spec;

import com.minguard.entity.TicketImage;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface TicketImageService {

    void uploadImages(List<MultipartFile> images, Long ticketId);

    List<TicketImage> findAllByTicketId(Long ticketId);

    void deleteAllByTicketId(Long ticketId);
}
