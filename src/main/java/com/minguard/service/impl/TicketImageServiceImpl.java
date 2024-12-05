package com.minguard.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.minguard.entity.Ticket;
import com.minguard.entity.TicketImage;
import com.minguard.repository.TicketImageRepository;
import com.minguard.service.spec.TicketImageService;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class TicketImageServiceImpl implements TicketImageService {

    private final AmazonS3 s3Client;
    private final TicketImageRepository ticketImageRepository;

    @Value("${cloud.aws.s3.bucket.incident}")
    private String bucketName;

    private void uploadImage(MultipartFile image, Long ticketId) {
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

        TicketImage ticketImage = new TicketImage();
        ticketImage.setTicket(new Ticket(ticketId));
        ticketImage.setKey(key);
        ticketImage.setUrl(url);
        ticketImageRepository.save(ticketImage);
    }

    @Override
    public void uploadImages(List<MultipartFile> images, Long ticketId) {
        images.forEach(image -> uploadImage(image, ticketId));
    }

    @Override
    public List<TicketImage> findAllByTicketId(Long ticketId) {
        return ticketImageRepository.findAllByTicketId(ticketId);
    }

    @Override
    public void deleteAllByTicketId(Long ticketId) {
        this.findAllByTicketId(ticketId).forEach(ticketImage -> {
            s3Client.deleteObject(bucketName, ticketImage.getKey());
            ticketImageRepository.delete(ticketImage);
        });
    }

}
