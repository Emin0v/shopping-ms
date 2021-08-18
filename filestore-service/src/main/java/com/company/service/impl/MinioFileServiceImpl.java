package com.company.service.impl;

import com.company.config.S3ConfigProperties;
import com.company.service.MinioFileService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioFileServiceImpl implements MinioFileService {

    private final MinioClient minioClient;
    private final S3ConfigProperties properties;

    @Override
    public void save(String id, String contentType, InputStream inputStream) {
         try {
             var object = PutObjectArgs.builder()
                     .object(id)
                     .contentType(contentType)
                     .stream(inputStream, inputStream.available(), -1)
                     .bucket(properties.getBucket())
                     .build();

             minioClient.putObject(object);

         }catch (Exception ex){
             log.error("File put error {}", ex);
         }
    }

    @Override
    public void delete(String id) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(properties.getBucket())
                    .object(id)
                    .build());

        }catch (Exception ex){
            log.error("File delete error: {}", ex);
        }
    }

    @Override
    public byte[] get(String id) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(properties.getBucket())
                    .object(id)
                    .build()).readAllBytes();
        } catch (Exception e) {
            log.error("File get id: {} error: {}", id, e);
        }
        return new byte[0];
    }
}
