package com.company.service;

import com.company.dto.CartRespDto;
import com.company.dto.product.CartProductDto;

public interface CartService {

     CartRespDto findByCartId(String id);

     void createCart(String id);

     CartRespDto addProductToCart(String id, CartProductDto dto);
}
