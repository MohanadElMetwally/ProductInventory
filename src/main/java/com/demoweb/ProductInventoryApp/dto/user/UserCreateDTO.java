package com.demoweb.ProductInventoryApp.dto.user;

import com.demoweb.ProductInventoryApp.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
