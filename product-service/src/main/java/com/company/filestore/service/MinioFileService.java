package com.company.filestore.service;

import java.io.InputStream;

public interface MinioFileService {

    void save(String id, String contentType, InputStream isFile);

    void delete(String id);

    byte[] get(String id);
}
