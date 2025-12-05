package com.demoweb.ProductInventoryApp.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id){
        super("User with id: %d not found".formatted(id));
    }
}
