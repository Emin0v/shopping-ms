package com.company.exception;

public class UserIsNotActiveException extends RuntimeException{

    public static final String MESSAGE = "The user is not active";

    public UserIsNotActiveException() {
        super(MESSAGE);
    }
}
