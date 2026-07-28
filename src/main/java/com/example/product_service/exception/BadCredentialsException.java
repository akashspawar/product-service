package com.example.product_service.exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String message){
        super(message);
    }
}
