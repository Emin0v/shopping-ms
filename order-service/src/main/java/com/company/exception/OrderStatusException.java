package com.company.exception;

import com.company.entity.OrderStatus;

public class OrderStatusException extends RuntimeException {
    public static final String MESSAGE = "Invalid order status: [%s]";

    public OrderStatusException(OrderStatus status) {
        super(String.format(MESSAGE, status));
    }
}
