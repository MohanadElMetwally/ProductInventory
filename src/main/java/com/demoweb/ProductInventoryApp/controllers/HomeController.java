package com.demoweb.ProductInventoryApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String greet(
        @RequestParam(name = "param-req", required = false, defaultValue = "User") String param) {
        return String.format("Welcome to Demo Web Application %s", param);
    }

}
