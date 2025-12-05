package com.demoweb.ProductInventoryApp.dto.user;

import com.demoweb.ProductInventoryApp.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Role role;
}
