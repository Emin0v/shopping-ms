package com.company.service.adapter;

import com.company.dto.CartRespDto;
import com.company.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartAdapter {

    CartRespDto map(Cart cart);

}
