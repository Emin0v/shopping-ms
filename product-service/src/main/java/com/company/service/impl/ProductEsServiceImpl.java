package com.company.service.impl;

import com.company.dto.product.ProductPriceResDto;
import com.company.model.MoneyTypes;
import com.company.model.Product;
import com.company.model.category.Category;
import com.company.model.es.CompanyEs;
import com.company.model.ProductImage;
import com.company.model.es.CategoryEs;
import com.company.model.es.ProductEs;
import com.company.repository.es.ProductEsRepository;
import com.company.service.CategoryService;
import com.company.service.ProductEsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductEsServiceImpl implements ProductEsService {

    private final ProductEsRepository productEsRepository;
    private final CategoryService categoryService;

    @Override
    public Mono<ProductEs> saveNewProduct(Product product) {
        return productEsRepository.save(
                ProductEs.builder()
                        .active(product.getActive())
                        .code(product.getCode())
                        .description(product.getDescription())
                        .features(product.getFeatures())
                        .id(product.getId())
                        .price(product.getPrice())
                        .name(product.getName())
                        .seller(CompanyEs.builder().id(product.getCompanyId()).name("Test").build())
                        .images(product.getProductImage()
                                .stream()
                                .map(ProductImage::getUrl).collect(Collectors.toList()))
                        .category(getProductCategory(product.getCategoryId()))
                        .build());
    }

    private CategoryEs getProductCategory(String categoryId) {
        Category category = categoryService.getById(categoryId);
        return CategoryEs.builder().name(category.getName()).id(category.getId()).code(category.getCode()).build();
    }

    @Override
    public Flux<ProductEs> findAll() {
        return productEsRepository.findAll();
    }

    @Override
    public Flux<ProductEs> findAllByCategoryId(String categoryId) {
        return productEsRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public Mono<ProductEs> findById(String id) {
        return productEsRepository.findById(id);
    }

    @Override
    public Set<ProductPriceResDto> getProductPrice(Set<String> productUuids) {
        return productEsRepository.
                findAllByIdIn(new ArrayList<>(productUuids))
                .toStream()
                .map(productEs -> new ProductPriceResDto(productEs.getId(),productEs.getPrice().get(MoneyTypes.USD)))
                .collect(Collectors.toSet());
    }
}
