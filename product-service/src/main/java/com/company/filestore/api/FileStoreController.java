package com.company.filestore.api;

import com.company.filestore.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/filestore")
@RequiredArgsConstructor
@CrossOrigin
public class FileStoreController {

    private final FileStoreService fileStoreService;

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("id") String id) throws Exception {
        Mono<byte[]> mono = fileStoreService.getImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new ByteArrayInputStream(mono.block())));
    }

}
