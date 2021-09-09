package com.company.exception;

public class InsufficientFundException extends RuntimeException {

    public static final String MESSAGE = "Insufficient fund";

    public InsufficientFundException() {
        super(MESSAGE);
    }

}
