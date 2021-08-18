package com.company.service.impl;

import com.company.service.FileStoreService;
import com.company.service.MinioFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStoreServiceImpl implements FileStoreService {

    private final MinioFileService minioFileService;

    @Override
    public Mono<byte[]> getImage(String id) throws Exception {
        return Mono.just(minioFileService.get(id));
    }

    @Override
    public void saveImage(String id, InputStream inputStream) {
        minioFileService.save(id, MediaType.IMAGE_JPEG_VALUE, inputStream);
    }

    @Override
    public void deleteImage(String id) {
        minioFileService.delete(id);
    }
}
