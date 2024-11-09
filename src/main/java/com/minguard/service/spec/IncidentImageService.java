package com.minguard.service.spec;

import com.minguard.entity.IncidentImage;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IncidentImageService {

    void uploadImages(List<MultipartFile> images, Long incidentId);

    List<IncidentImage> findAllByIncidentId(Long incidentId);

    void deleteAllByIncidentId(Long incidentId);
}
