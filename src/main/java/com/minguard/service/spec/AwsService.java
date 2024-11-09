package com.minguard.service.spec;

import com.amazonaws.AmazonClientException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public interface AwsService {

    void uploadFile(
            final String bucketName,
            final String keyName,
            final Long contentLength,
            final String contentType,
            final InputStream value
    ) throws AmazonClientException;

    ByteArrayOutputStream downloadFile(
            final String bucketName,
            final String keyName
    ) throws IOException, AmazonClientException;

    void deleteFile(
            final String bucketName,
            final String keyName
    ) throws AmazonClientException;
}
