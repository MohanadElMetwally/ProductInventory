package com.demoweb.ProductInventoryApp.dto.product;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ProductUpdateDTO {
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String brand;
    @Nullable
    private BigDecimal price;
    @Nullable
    private String category;
    @Nullable
    private Date releaseDate;
    @Nullable
    private Boolean available;
    @Nullable
    private Integer quantity;
}
