package com.demoweb.ProductInventoryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demoweb.ProductInventoryApp.dto.TokenDTO;
import com.demoweb.ProductInventoryApp.services.UserService;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<TokenDTO> login(@RequestParam String username,
        @RequestParam String password) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.verify(username, password));
    }
}
