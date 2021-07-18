package com.company.service.adapter;

import com.company.dto.OrderResDto;
import com.company.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderAdapter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_date", ignore = true)
    OrderResDto map(Order order);

}
