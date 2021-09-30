package com.company.service.impl;

import com.company.dto.CartRespDto;
import com.company.dto.product.CartProductDto;
import com.company.entity.Cart;
import com.company.repo.CartRepository;
import com.company.service.CartService;
import com.company.service.adapter.CartAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartAdapter cartAdapter;

    @Override
    public CartRespDto findByCartId(String id) {
        Cart cart = cartRepository.findById(id).get();

        return cartAdapter.map(cart);
    }

    @Override
    @Transactional
    public void createCart(String id) {
        cartRepository.save(Cart.builder().cartId(id).build());
    }

    @Override
    @Transactional
    public boolean addProductToCart(CartProductDto cartProductDto) {
        Cart cart = Cart.builder()
                .cartId(cartProductDto.getCartId())
                .products(Map.of(cartProductDto.getProductId(),cartProductDto.getCount()))
                .build();

        cartRepository.save(cart);

        return true;
    }
}
