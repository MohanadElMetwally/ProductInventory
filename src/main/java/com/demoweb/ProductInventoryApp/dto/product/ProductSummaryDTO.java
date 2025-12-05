package com.demoweb.ProductInventoryApp.dto.product;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class ProductSummaryDTO {
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private Boolean available;
    private Integer quantity;
    private Integer userId;
}
