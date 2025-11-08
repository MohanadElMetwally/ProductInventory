package com.demoweb.ProductInventoryApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MessageDTO implements ApiResponse {
    private String message;
}
