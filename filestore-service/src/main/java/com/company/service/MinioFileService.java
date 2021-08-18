package com.company.service;

import java.io.InputStream;

public interface MinioFileService {

    void save(String id, String contentType, InputStream inputStream);

    void delete(String id);

    byte[] get(String id);

}
