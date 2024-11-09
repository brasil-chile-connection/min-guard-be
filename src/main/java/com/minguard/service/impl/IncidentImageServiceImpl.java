package com.minguard.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.minguard.entity.Incident;
import com.minguard.entity.IncidentImage;
import com.minguard.repository.IncidentImageRepository;
import com.minguard.service.spec.IncidentImageService;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class IncidentImageServiceImpl implements IncidentImageService {

    private final AmazonS3 s3Client;
    private final IncidentImageRepository incidentImageRepository;

    @Value("${cloud.aws.s3.bucket.incident}")
    private String bucketName;

    private void uploadImage(MultipartFile image, Long incidentId) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());
        metadata.setContentDisposition("inline");

        String url;
        String key = UUID.randomUUID().toString();
        try {
            s3Client.putObject(bucketName, key, image.getInputStream(), metadata);
            s3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
            url = s3Client.getUrl(bucketName, key).toExternalForm();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IncidentImage incidentImage = new IncidentImage();
        incidentImage.setIncident(new Incident(incidentId));
        incidentImage.setKey(key);
        incidentImage.setUrl(url);
        incidentImageRepository.save(incidentImage);
    }

    @Override
    public void uploadImages(List<MultipartFile> images, Long incidentId) {
        images.forEach(image -> uploadImage(image, incidentId));
    }

    @Override
    public List<IncidentImage> findAllByIncidentId(Long incidentId) {
        return incidentImageRepository.findAllByIncidentId(incidentId);
    }

    @Override
    public void deleteAllByIncidentId(Long incidentId) {
        this.findAllByIncidentId(incidentId).forEach(incidentImage -> {
            s3Client.deleteObject(bucketName, incidentImage.getKey());
            incidentImageRepository.delete(incidentImage);
        });
    }

}
