package com.demoweb.ProductInventoryApp.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDTO {
    private String username;
    private String password;
}
