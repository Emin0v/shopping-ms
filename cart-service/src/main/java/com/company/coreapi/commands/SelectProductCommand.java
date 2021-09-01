package com.company.coreapi.commands;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectProductCommand {

    @TargetAggregateIdentifier
    private UUID cartId;

    @NotNull
    private UUID productId;

    @NotNull
    private Integer quantity;

}
