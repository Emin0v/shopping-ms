package com.company.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.InputStream;

@FeignClient("filestore-service")
public interface FileStoreServiceClient {

    @GetMapping("filestore/{id}")
    Mono<Void> getImage(@PathVariable("id") String id, ServerWebExchange serverWebExchange)
            throws Exception;

    @PostMapping("filestore/{id}")
    void saveImage(@PathVariable(value = "id", required = false) String id,
                          @RequestBody InputStream inputStream);

    }