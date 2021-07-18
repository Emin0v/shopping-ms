package com.company.service.adapter;

import com.company.dto.OrderProductCreateReqDto;
import com.company.entity.OrderProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductAdapter {

    OrderProduct map(OrderProductCreateReqDto dto);


}
