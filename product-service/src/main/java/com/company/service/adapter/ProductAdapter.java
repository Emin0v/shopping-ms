package com.company.service.adapter;


import com.company.dto.ProductCreateReqDto;
import com.company.dto.ProductResDto;
import com.company.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductAdapter {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "id", ignore = true)
    Product map(ProductCreateReqDto productCreateReqDto);

    ProductResDto map(Product product);
}
