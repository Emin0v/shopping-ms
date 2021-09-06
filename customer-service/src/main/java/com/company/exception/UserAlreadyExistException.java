package com.company.exception;

public class UserAlreadyExistException extends RuntimeException {

    public static final String MESSAGE = "Customer with given userUuid [%s] is already registered";

    public UserAlreadyExistException(String uuid) {
        super(String.format(MESSAGE, uuid));
    }
}
