package com.company.service.impl;

import com.company.exception.ProductNotFoundException;
import com.company.dto.ProductCreateReqDto;
import com.company.dto.ProductResDto;
import com.company.entity.Product;
import com.company.repository.ProductRepository;
import com.company.service.ProductService;
import com.company.service.adapter.ProductAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    @Override
    public ProductResDto create(ProductCreateReqDto productCreateReqDto) {
        Product product = productAdapter.map(productCreateReqDto);
        return  productAdapter.map(productRepository.save(product));
    }

    @Override
    public Page<ProductResDto> search(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .map(productAdapter::map);
    }

    @Override
    public ProductResDto getById(String uuid) {
        return productRepository
                .findByUuid(uuid)
                .map(productAdapter::map)
                .orElseThrow(()->new ProductNotFoundException("uuid"));
    }

    @Override
    public void delete(String uuid) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(()->new ProductNotFoundException("uuid"));
        productRepository.delete(product);
    }
}
