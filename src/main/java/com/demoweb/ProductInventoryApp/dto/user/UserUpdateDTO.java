package com.demoweb.ProductInventoryApp.dto.user;

import com.demoweb.ProductInventoryApp.enums.Role;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDTO {
    @Nullable
    public String username;

    @Nullable
    public Role role;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private Boolean isActive;

}
