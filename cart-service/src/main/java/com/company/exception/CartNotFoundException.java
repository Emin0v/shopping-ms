package com.company.exception;

public class CartNotFoundException extends NotFoundException{

    public static final String MESSAGE = "Cart not found ";

    public CartNotFoundException() {
        super(MESSAGE);
    }
}
