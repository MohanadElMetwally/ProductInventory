package com.demoweb.ProductInventoryApp.DTOs;

import org.springframework.stereotype.Component;

import com.demoweb.ProductInventoryApp.Enums.Role;

import lombok.Data;

@Data
@Component
public class UserCreateDTO {
    private String username;
    private String password;
    private Role role;
}
