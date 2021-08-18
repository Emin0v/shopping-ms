package com.company;

public class ProductNotFoundException extends RuntimeException{

    private static final String MESSAGE = "Product not found ";

    public ProductNotFoundException(){
        super(MESSAGE);
    }

    public ProductNotFoundException(String uuid){
        super(MESSAGE+":"+uuid);
    }
}
