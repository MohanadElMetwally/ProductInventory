package com.demoweb.ProductInventoryApp.dto.product;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductCreateDTO {
    @NotBlank(message = "name is required")
    private String name;
    private String description;
    @NotBlank(message = "brand is required")
    private String brand;
    @Positive(message = "price must be positive")
    private BigDecimal price;
    @NotBlank(message = "category is required")
    private String category;
    @NotNull(message = "release date is required")
    private Date releaseDate;
    @NotNull(message = "available is required")
    private Boolean available;
    @NotNull(message = "quantity is required")
    private Integer quantity;
}
