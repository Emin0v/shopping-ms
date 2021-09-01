package com.company.projector;

import com.company.coreapi.domain.Cart;
import com.company.coreapi.events.CartCreatedEvent;
import com.company.coreapi.query.FindCartQuery;
import com.company.coreapi.repository.CartRepository;
import com.company.dto.CartDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

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

    @QueryHandler
    @Transactional
    public CartDTO handle(FindCartQuery query){
        Cart cart = cartRepository.findById(query.getCartId()).orElse(null);
        return modelMapper.map(cart, CartDTO.class);
    }

}
