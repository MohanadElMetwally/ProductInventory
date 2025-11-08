package com.demoweb.ProductInventoryApp.Mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.demoweb.ProductInventoryApp.DTOs.UserDTO;
import com.demoweb.ProductInventoryApp.models.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    public void updateUser(UserDTO dto, @MappingTarget Users user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void patchUser(UserDTO dto, @MappingTarget Users user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    public Users toEntity(UserDTO dto);

    public UserDTO toDTO(Users user);
}
