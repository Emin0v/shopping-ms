package com.company.dto;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class CartView {

    private UUID cartId;
    private Map<UUID, Integer> products;

}
