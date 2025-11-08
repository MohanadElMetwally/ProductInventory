package com.demoweb.ProductInventoryApp.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.demoweb.ProductInventoryApp.DTOs.UserCreateDTO;
import com.demoweb.ProductInventoryApp.models.Users;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {
    @Mapping(target = "id", ignore = true)
    public Users toEntity(UserCreateDTO dto);
}
