package com.company.service;

import com.company.dto.ProductCreateReqDto;
import com.company.dto.ProductResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResDto create(ProductCreateReqDto productCreateReqDto);

    List<ProductResDto> getAll(int pageNo, int pageSize);

    List<ProductResDto> getAllSorted();

    ProductResDto getById(String uuid);

    void delete(String uuid);

}
