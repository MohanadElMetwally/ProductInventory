package com.demoweb.ProductInventoryApp.dto.user;

import com.demoweb.ProductInventoryApp.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDTO {
    private String username;
    private Role role;
    private String firstName;
    private String lastName;
    private Boolean isActive;
}
