package com.company.filestore.startup;

import com.company.filestore.config.S3ConfigProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStoreStartup {

    private final MinioClient minioClient;
    private final S3ConfigProperties properties;

    @EventListener(ApplicationStartedEvent.class)
    public void init() throws Exception {
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(properties.getBucket())
                .build());

        if(!bucketExists){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucket()).build());
        }
    }

}
