package com.demoweb.ProductInventoryApp.models;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name is required")
    private String name;

    private String description;

    @NotNull(message = "brand is required")
    private String brand;

    @Positive(message = "Price cant be negative")
    private BigDecimal price;

    @NotNull(message = "category is required")
    private String category;

    @NotNull(message = "release date is required")
    private Date releaseDate;

    @NotNull
    private Boolean available;

    @NotNull
    private Integer quantity;

}
