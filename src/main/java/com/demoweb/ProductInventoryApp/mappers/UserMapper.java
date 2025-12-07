package com.demoweb.ProductInventoryApp.mappers;

import org.springframework.stereotype.Component;

import com.demoweb.ProductInventoryApp.dto.user.UserCreateDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserUpdateDTO;
import com.demoweb.ProductInventoryApp.models.Users;

@Component
public class UserMapper {

    public Users toEntity(UserCreateDTO dto) {
        if (dto == null)
            return null;

        return Users.builder()
            .username(dto.getUsername())
            .role(dto.getRole())
            .password(dto.getPassword())
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .build();
    }

    public UserDTO toDTO(Users user) {
        if (user == null)
            return null;

        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .role(user.getRole())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .isActive(user.getIsActive())
            .build();
    }

    public void updateUser(UserUpdateDTO dto, Users user) {
        if (dto == null || user == null)
            return;

        if (dto.getUsername() != null)
            user.setUsername(dto.getUsername());

        if (dto.getRole() != null)
            user.setRole(dto.getRole());

        if (dto.getFirstName() != null)
            user.setFirstName(dto.getFirstName());

        if (dto.getLastName() != null)
            user.setLastName(dto.getLastName());

        if (dto.getIsActive() != null)
            user.setIsActive(dto.getIsActive());
    }
}
