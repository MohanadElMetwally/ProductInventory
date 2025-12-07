package com.demoweb.ProductInventoryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoweb.ProductInventoryApp.annotations.CurrentUser;
import com.demoweb.ProductInventoryApp.dto.MessageDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserCreateDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserUpdateDTO;
import com.demoweb.ProductInventoryApp.dto.user.UsersDTO;
import com.demoweb.ProductInventoryApp.enums.Role;
import com.demoweb.ProductInventoryApp.models.Users;
import com.demoweb.ProductInventoryApp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsersDTO> readUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> readUserMe(@CurrentUser Users user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.userToDTO(user));
    }

    @GetMapping("/admin-action")
    public ResponseEntity<MessageDTO> AdminDashboard(
        @CurrentUser(requiredRole = Role.ADMIN) Users user) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(new MessageDTO("Welcome to your admin dashboard, " + user.getFirstName() + "!"));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(userDTO));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> userPatch(@PathVariable int id,
        @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.userUpdate(id, userUpdateDTO));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> userPatchMe(@CurrentUser Users user,
        @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.userUpdateMe(user, userUpdateDTO));
    }

    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> deactivateUser(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deactivateUser(id));
    }

    @PatchMapping("/deactivate-me")
    public ResponseEntity<UserDTO> deactivateUserMe(@CurrentUser Users user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deactivateUserMe(user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDTO> userDelete(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new MessageDTO("Deleted user successfully."));
    }
}
