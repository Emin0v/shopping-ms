package com.company.api;

import com.company.dto.product.ProductDetailResponse;
import com.company.dto.product.ProductResponse;
import com.company.service.ProductService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ProductCtrl.CTRL)
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductDetailResponse> getProductDetail(@PathVariable("id") String id) {
        return productService.getProductDetail(id);
    }



}
