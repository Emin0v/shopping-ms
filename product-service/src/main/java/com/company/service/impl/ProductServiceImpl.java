package com.company.service.impl;

import com.company.exception.ProductNotFoundException;
import com.company.dto.ProductCreateReqDto;
import com.company.dto.ProductResDto;
import com.company.entity.Product;
import com.company.repository.ProductRepository;
import com.company.service.ProductService;
import com.company.service.adapter.ProductAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    @Override
    @Transactional
    public ProductResDto create(ProductCreateReqDto productCreateReqDto) {
        Product product = productAdapter.map(productCreateReqDto);
        return  productAdapter.map(productRepository.save(product));
    }

    @Override
    public List<ProductResDto> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);

        return productRepository
                .findAll(pageable)
                .map(productAdapter::map).getContent();
    }

    @Override
    public List<ProductResDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"title");

        return productRepository
                .findAll(sort)
                .stream().map(productAdapter::map)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResDto getById(String uuid) {
        return productRepository
                .findByUuid(uuid)
                .map(productAdapter::map)
                .orElseThrow(()->new ProductNotFoundException("uuid"));
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(()->new ProductNotFoundException("uuid"));
        productRepository.delete(product);
    }
}
