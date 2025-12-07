package com.demoweb.ProductInventoryApp.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demoweb.ProductInventoryApp.dto.product.ProductCreateDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductSummaryDTO;
import com.demoweb.ProductInventoryApp.dto.product.ProductUpdateDTO;
import com.demoweb.ProductInventoryApp.models.Product;
import com.demoweb.ProductInventoryApp.models.Users;

@Component
public class ProductMapper {
    @Autowired
    UserMapper userMapper;

    public Product toEntity(ProductCreateDTO dto, Users user) {
        if (dto == null)
            return null;

        return Product.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .brand(dto.getBrand())
            .price(dto.getPrice())
            .category(dto.getCategory())
            .releaseDate(dto.getReleaseDate())
            .available(dto.getAvailable())
            .quantity(dto.getQuantity())
            .user(user)
            .build();
    }

    public ProductDTO toDTO(Product entity) {
        if (entity == null)
            return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBrand(entity.getBrand());
        dto.setPrice(entity.getPrice());
        dto.setCategory(entity.getCategory());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setAvailable(entity.getAvailable());
        dto.setQuantity(entity.getQuantity());
        dto.setUser(userMapper.toDTO(entity.getUser()));

        return dto;
    }

    public ProductSummaryDTO toSummaryDTO(Product entity) {
        if (entity == null)
            return null;

        ProductSummaryDTO dto = new ProductSummaryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setBrand(entity.getBrand());
        dto.setPrice(entity.getPrice());
        dto.setCategory(entity.getCategory());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setAvailable(entity.getAvailable());
        dto.setQuantity(entity.getQuantity());
        dto.setUserId(userMapper.toDTO(entity.getUser()).getId());

        return dto;
    }

    public void updateProduct(ProductUpdateDTO dto, Product entity) {
        if (dto == null || entity == null)
            return;

        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getDescription() != null)
            entity.setDescription(dto.getDescription());
        if (dto.getBrand() != null)
            entity.setBrand(dto.getBrand());
        if (dto.getPrice() != null)
            entity.setPrice(dto.getPrice());
        if (dto.getCategory() != null)
            entity.setCategory(dto.getCategory());
        if (dto.getReleaseDate() != null)
            entity.setReleaseDate(dto.getReleaseDate());
        if (dto.getAvailable() != null)
            entity.setAvailable(dto.getAvailable());
        if (dto.getQuantity() != null)
            entity.setQuantity(dto.getQuantity());
    }
}
