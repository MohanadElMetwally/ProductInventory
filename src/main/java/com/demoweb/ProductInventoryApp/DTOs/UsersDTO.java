package com.demoweb.ProductInventoryApp.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsersDTO {
    List<UserDTO> users;
}
