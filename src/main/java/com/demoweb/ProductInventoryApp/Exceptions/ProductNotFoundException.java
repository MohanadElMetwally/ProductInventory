package com.demoweb.ProductInventoryApp.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product with id: %d not found".formatted(id));
    }
}
