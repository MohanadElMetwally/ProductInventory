package com.demoweb.ProductInventoryApp.Exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(int id){
        super("Product with id: %d not found".formatted(id));
    }
}
