package com.company.controllers;


import com.company.coreapi.commands.CreateCartCommand;
import com.company.coreapi.commands.DeSelectProductCommand;
import com.company.coreapi.commands.SelectProductCommand;
import com.company.coreapi.query.FindCartQuery;
import com.company.dto.CartDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ProductOrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public UUID handle(){
        log.info("Received cart action");
        return commandGateway.sendAndWait(new CreateCartCommand());
    }

    @PostMapping("/add")
    public void handle(@RequestBody SelectProductCommand command){
        log.info("Received select product action");
        commandGateway.send(command);
    }

    @PostMapping("/remove")
    public void handle(@RequestBody DeSelectProductCommand command) {
        log.info("Received de select product action");
        commandGateway.send(command);
    }

    @GetMapping("/{cartId}")
    public CompletableFuture<CartDTO> handle(@PathVariable("cartId") String cartId) {
        return queryGateway.query(new FindCartQuery(UUID.fromString(cartId)),
                ResponseTypes.instanceOf(CartDTO.class));
    }


}
