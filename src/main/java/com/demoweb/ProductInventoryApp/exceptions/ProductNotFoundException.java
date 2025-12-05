package com.demoweb.ProductInventoryApp.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product with id: %d not found".formatted(id));
    }
}
