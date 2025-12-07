package com.demoweb.ProductInventoryApp.exceptions;

public class ForbiddenAdminOperationException extends RuntimeException {
    public ForbiddenAdminOperationException(String message) {
        super(message);
    }
}
