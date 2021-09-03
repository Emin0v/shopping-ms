package com.company.aggregates;

import com.company.coreapi.commands.CreateCartCommand;
import com.company.coreapi.commands.DeSelectProductCommand;
import com.company.coreapi.commands.SelectProductCommand;
import com.company.coreapi.events.CartCreatedEvent;
import com.company.coreapi.events.ProductDeSelectedEvent;
import com.company.coreapi.events.ProductSelectedEvent;
import com.company.coreapi.exception.ProductDeSelectedException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Data
@Aggregate
@NoArgsConstructor
public class CartAggregate {

    @AggregateIdentifier
    private UUID cardId;
    private Map<UUID, Integer> selectedProducts;

    @CommandHandler
    public CartAggregate(CreateCartCommand command) {
        log.info("Received create food cart command");
        AggregateLifecycle.apply(new CartCreatedEvent(command.getCartId()));
    }

    @CommandHandler
    public void handle(SelectProductCommand command) {
        log.info("Received select product command {}", command);
        AggregateLifecycle.apply(ProductSelectedEvent.builder()
                .cartId(command.getCartId())
                .productId(command.getProductId())
                .quantity(command.getQuantity())
                .build()
        );
    }

    @CommandHandler
    public void handle(DeSelectProductCommand command) {
        log.info("Received deselect product command {}", command);
        AggregateLifecycle.apply(ProductDeSelectedEvent.builder()
                .cartId(command.getCartId())
                .productId(command.getProductId())
                .quantity(command.getQuantity())
                .build()
        );
    }

    @EventSourcingHandler
    public void on(CartCreatedEvent event) {
        log.info("Processing food cart created event {}", event.getCartId());
        cardId = event.getCartId();
        selectedProducts = new HashMap<>();
    }

    @EventSourcingHandler
    public void on(ProductSelectedEvent event) {
        log.info("Processing product selection event {}", event);
        selectedProducts.merge(event.getProductId(), event.getQuantity(), Integer::sum);
        log.info("The cart state is {}", this);
    }

    @EventSourcingHandler
    public void on(ProductDeSelectedEvent event) {
        log.info("Processing product deselection event {}", event);
        if (!selectedProducts.containsKey(event.getProductId())) {
            throw new ProductDeSelectedException();
        }

        Integer value = selectedProducts.get(event.getProductId());
        if (value - event.getQuantity() < 0) {
            selectedProducts.put(event.getProductId(), 0);
        } else {
            selectedProducts.put(event.getProductId(), value - event.getQuantity());
        }

        log.info("The cart state is {}", this);
    }


}
