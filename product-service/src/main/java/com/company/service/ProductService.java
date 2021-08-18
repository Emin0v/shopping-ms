package com.company.service;

import com.company.dto.product.ProductDetailResponse;
import com.company.dto.product.ProductResponse;
import com.company.dto.product.ProductSaveRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

     Flux<ProductResponse> getAll();

     Flux<ProductResponse> getAllByCategoryId(String categoryId);

     ProductResponse save(ProductSaveRequest request);

     Mono<Long> count();

     Mono<ProductDetailResponse> getProductDetail(String id);
}
