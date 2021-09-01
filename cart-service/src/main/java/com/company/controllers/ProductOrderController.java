package com.company.controllers;


import com.company.coreapi.commands.CreateCartCommand;
import com.company.coreapi.commands.DeSelectProductCommand;
import com.company.coreapi.commands.SelectProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ProductOrderController {

    private final CommandGateway commandGateway;

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



}
