package com.company.filestore.service;

import reactor.core.publisher.Mono;

import java.io.InputStream;

public interface FileStoreService {

    Mono<byte[]> getImage(String id) throws Exception;

    void saveImage(String id, InputStream isFile);

    void deleteImage(String id);

}
