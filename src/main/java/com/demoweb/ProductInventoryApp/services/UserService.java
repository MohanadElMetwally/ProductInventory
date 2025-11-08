package com.demoweb.ProductInventoryApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demoweb.ProductInventoryApp.DTOs.UserCreateDTO;
import com.demoweb.ProductInventoryApp.DTOs.UserDTO;
import com.demoweb.ProductInventoryApp.Exceptions.UserNotFoundException;
import com.demoweb.ProductInventoryApp.Mappers.UserCreateMapper;
import com.demoweb.ProductInventoryApp.Mappers.UserMapper;
import com.demoweb.ProductInventoryApp.Repository.UserRepo;
import com.demoweb.ProductInventoryApp.models.Users;

@Service
public class UserService {

    UserRepo userRepo;
    // TODO: remove mappers and use builder pattern instead
    UserMapper userMapper;
    UserCreateMapper userCreateMapper;
    BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, UserMapper userMapper, UserCreateMapper userCreateMapper,
        BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.userCreateMapper = userCreateMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getUsers() {
        List<UserDTO> users = userRepo.findAll().stream().map(userMapper::toDTO).toList();
        return users;
    }

    public UserDTO getUserById(int id) throws UserNotFoundException {
        Optional<Users> existingUser = userRepo.findById(id);

        if (existingUser.isEmpty())
            throw new UserNotFoundException(id);
        return userMapper.toDTO(existingUser.get());
    }

    public UserDTO registerUser(UserCreateDTO userCreateDTO) {
        Users userCreate = userCreateMapper.toEntity(userCreateDTO);
        userCreate.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        Users user = userRepo.save(userCreate);
        return userMapper.toDTO(user);
    }

}
