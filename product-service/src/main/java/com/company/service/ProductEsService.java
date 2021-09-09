package com.company.service;

import com.company.dto.product.ProductPriceResDto;
import com.company.model.Product;
import com.company.model.es.ProductEs;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface ProductEsService {

    Mono<ProductEs> saveNewProduct(Product product);

    Flux<ProductEs> findAll();

    Flux<ProductEs> findAllByCategoryId(String categoryId);

    Mono<ProductEs> findById(String id);

    Set<ProductPriceResDto> getProductPrice(Set<String> productUuids);

}
