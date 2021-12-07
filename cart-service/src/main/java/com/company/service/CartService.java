package com.company.service;

import com.company.dto.CartRespDto;
import com.company.dto.product.CartProductDto;

public interface CartService {

     CartRespDto findByCartId(String id);

     void createCart(String id);

     boolean addProductToCart(CartProductDto dto);

     void deleteProductFromCart(String cartId, String productId);

}
