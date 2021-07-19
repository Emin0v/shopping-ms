package com.company.service.adapter;

import com.company.dto.OrderResDto;
import com.company.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderAdapter {

    OrderResDto map(Order order);

}
