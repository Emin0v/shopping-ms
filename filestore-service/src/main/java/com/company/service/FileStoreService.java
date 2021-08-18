package com.company.service;

import reactor.core.publisher.Mono;

import java.io.InputStream;

public interface FileStoreService {

    Mono<byte[]> getImage(String id) throws Exception;

    void saveImage(String id, InputStream inputStream);

    void deleteImage(String id);
}
