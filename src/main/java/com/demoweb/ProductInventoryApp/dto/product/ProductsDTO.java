package com.demoweb.ProductInventoryApp.dto.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsDTO {
    private List<ProductDTO> products;
}
