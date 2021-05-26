package com.ehago.kreamzone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.ByteBuffer;

@Component
public class S3UploadProvider {

    private final S3Client S3 = S3Client.builder().region(Region.AP_NORTHEAST_2).build();

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public boolean upload(String key, MultipartFile multipartFile) {
        boolean successful = false;
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            ByteBuffer byteBuffer = ByteBuffer.wrap(multipartFile.getBytes());
            RequestBody requestBody = RequestBody.fromByteBuffer(byteBuffer);
            successful = S3.putObject(putObjectRequest, requestBody).sdkHttpResponse().isSuccessful();
        } catch (IOException | AwsServiceException | SdkClientException e) {
            e.printStackTrace();
        }

        return successful;
    }

    public boolean delete(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        return S3.deleteObject(deleteObjectRequest).sdkHttpResponse().isSuccessful();
    }

}
