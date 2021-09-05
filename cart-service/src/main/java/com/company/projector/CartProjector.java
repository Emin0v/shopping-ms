package com.company.projector;

import com.company.coreapi.domain.Cart;
import com.company.coreapi.events.CartCreatedEvent;
import com.company.coreapi.events.ProductSelectedEvent;
import com.company.coreapi.query.FindCartQuery;
import com.company.coreapi.repository.CartRepository;
import com.company.dto.CartView;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CartProjector {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @EventHandler
    public void on(CartCreatedEvent event) {
        cartRepository.save(
                Cart.builder()
                        .cartId(event.getCartId())
                        .products(Collections.emptyMap())
                        .build()
        );
    }

    @EventHandler
    @Transactional
    public void on(ProductSelectedEvent event){
        cartRepository.save(
                Cart.builder()
                        .cartId(event.getCartId())
                        .products(Map.of(event.getProductId(),event.getQuantity()))
                        .build()
        );
    }

    @QueryHandler
    @Transactional
    public CartView handle(FindCartQuery query){
        System.out.println("Cart id = "+ query.getCartId());
        Cart cart = cartRepository.findById(query.getCartId()).orElse(null);
        return modelMapper.map(cart, CartView.class);
    }

}
