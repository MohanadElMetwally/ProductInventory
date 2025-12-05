package com.demoweb.ProductInventoryApp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenDTO {
    private String accessToken;
    private String tokenType = "bearer";

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
