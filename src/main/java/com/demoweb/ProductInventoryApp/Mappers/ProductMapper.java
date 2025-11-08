package com.demoweb.ProductInventoryApp.Mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.demoweb.ProductInventoryApp.DTOs.ProductDTO;
import com.demoweb.ProductInventoryApp.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    public void updateProduct(ProductDTO dto, @MappingTarget Product entity);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void patchProduct(ProductDTO dto, @MappingTarget Product entity);

    @Mapping(target = "id", ignore = true)
    public Product toEntity(ProductDTO dto);

    public ProductDTO toDTO(Product prod);
}
