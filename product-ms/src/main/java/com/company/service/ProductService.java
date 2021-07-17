package com.company.service;

import com.company.dto.ProductCreateReqDto;
import com.company.dto.ProductResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResDto create(ProductCreateReqDto productCreateReqDto);

    Page<ProductResDto> search(Pageable pageable);

    ProductResDto getById(String uuid);

    void delete(String uuid);

}
