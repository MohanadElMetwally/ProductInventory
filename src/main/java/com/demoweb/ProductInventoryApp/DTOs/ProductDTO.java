package com.demoweb.ProductInventoryApp.DTOs;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductDTO implements ApiResponse {
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private Boolean available;
    private Integer quantity;
}
