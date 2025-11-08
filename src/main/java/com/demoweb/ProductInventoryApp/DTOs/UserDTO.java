package com.demoweb.ProductInventoryApp.DTOs;

import org.springframework.stereotype.Component;

import com.demoweb.ProductInventoryApp.Enums.Role;

import lombok.Data;

@Data
@Component
public class UserDTO {
    private int Id;
    private String username;
    private Role role;
}
