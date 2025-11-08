package com.demoweb.ProductInventoryApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoweb.ProductInventoryApp.DTOs.UserDTO;
import com.demoweb.ProductInventoryApp.DTOs.UsersDTO;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/")
    public UsersDTO getUsers() {
        return new UsersDTO(null);
    }

    @PostMapping("/")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {

        return userDTO;
    }

}
