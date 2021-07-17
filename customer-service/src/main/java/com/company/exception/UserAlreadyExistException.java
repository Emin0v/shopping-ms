package com.company.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(){

    }

    public UserAlreadyExistException(String message){
        super(message);
    }
}
