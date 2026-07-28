package com.example.product_service.exception;

public class UserAlreadyExistsException extends RuntimeException{
    private String message;
    public UserAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }
}
