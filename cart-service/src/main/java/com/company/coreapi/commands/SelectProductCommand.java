package com.company.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectProductCommand {

    private UUID cartId;

    private UUID productId;

    private Integer quantity;
}
