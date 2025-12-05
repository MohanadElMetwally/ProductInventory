package com.demoweb.ProductInventoryApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demoweb.ProductInventoryApp.dto.TokenDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserCreateDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserPatchDTO;
import com.demoweb.ProductInventoryApp.dto.user.UserUpdateDTO;
import com.demoweb.ProductInventoryApp.dto.user.UsersDTO;
import com.demoweb.ProductInventoryApp.exceptions.UserNotFoundException;
import com.demoweb.ProductInventoryApp.mappers.UserMapper;
import com.demoweb.ProductInventoryApp.models.UserPrincipal;
import com.demoweb.ProductInventoryApp.models.Users;
import com.demoweb.ProductInventoryApp.repository.UserRepo;

@Service
public class UserService {
    UserRepo userRepo;
    UserMapper userMapper;
    BCryptPasswordEncoder passwordEncoder;
    AuthenticationManager authManager;
    JWTService jwtService;

    public UserService(UserRepo userRepo, UserMapper userMapper,
        BCryptPasswordEncoder passwordEncoder, AuthenticationManager authManager,
        JWTService jwtService) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public UsersDTO getUsers() {
        List<UserDTO> users = userRepo.findAll().stream().map(userMapper::toDTO).toList();
        return new UsersDTO(users);
    }

    public UserDTO getUserById(int id) {
        Optional<Users> existingUser = userRepo.findById(id);

        if (existingUser.isEmpty())
            throw new UserNotFoundException(id);
        return userMapper.toDTO(existingUser.get());
    }

    public UserDTO registerUser(UserCreateDTO userCreateDTO) {
        Users userCreate = userMapper.toEntity(userCreateDTO);
        userCreate.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        Users user = userRepo.save(userCreate);
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO userUpdate(int id, UserUpdateDTO dto) {
        Optional<Users> existingUser = userRepo.findById(id);

        if (existingUser.isEmpty())
            throw new UserNotFoundException(id);

        Users user = existingUser.get();
        userMapper.updateUser(dto, user);

        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO userUpdateMe(Users user, UserUpdateDTO dto) {
        userMapper.updateUser(dto, user);

        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO userPatch(int id, UserPatchDTO dto) {
        Optional<Users> existingUser = userRepo.findById(id);

        if (existingUser.isEmpty())
            throw new UserNotFoundException(id);

        Users user = existingUser.get();
        userMapper.patchUser(dto, user);

        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO userPatchMe(Users user, UserPatchDTO dto) {
        userMapper.patchUser(dto, user);

        return userMapper.toDTO(user);
    }

    @Transactional
    public void deleteUser(int id) {
        var existingUser = userRepo.findById(id);
        if (existingUser.isEmpty())
            throw new UserNotFoundException(id);

        Users user = existingUser.get();
        userRepo.delete(user);

    }

    @Transactional
    public UserDTO deactivateUser(int id) {
        Optional<Users> existingUser = userRepo.findById(id);

        if (existingUser.isEmpty())
            throw new UserNotFoundException(id);

        Users user = existingUser.get();
        user.setIsActive(false);

        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO deactivateUserMe(Users user) {
        user.setIsActive(false);
        return userMapper.toDTO(user);
    }

    public UserDTO userToDTO(Users user) {
        return userMapper.toDTO(user);
    }

    public TokenDTO verify(String username, String password) {

        Authentication authentication = authManager
            .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        Users user = ((UserPrincipal) authentication.getPrincipal()).getUser();

        return new TokenDTO(jwtService.generateToken(user));
    }
}
