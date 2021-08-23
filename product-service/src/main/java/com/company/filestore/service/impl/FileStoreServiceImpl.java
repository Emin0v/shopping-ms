package com.company.filestore.service.impl;

import com.company.filestore.service.FileStoreService;
import com.company.filestore.service.MinioFileService;
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
    public Mono<byte[]> getImage(String id) {
        return Mono.just(minioFileService.get(id));
    }

    @Override
    public void saveImage(String id, InputStream isFile) {
        minioFileService.save(id, MediaType.IMAGE_JPEG_VALUE, isFile);
    }

    @Override
    public void deleteImage(String id) {
        minioFileService.delete(id);
    }
}
