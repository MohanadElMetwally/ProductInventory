package com.demoweb.ProductInventoryApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demoweb.ProductInventoryApp.models.UserPrincipal;
import com.demoweb.ProductInventoryApp.models.Users;
import com.demoweb.ProductInventoryApp.repository.UserRepo;

@Service
public class UsersDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                "User with username: %s was not found".formatted(username));
        }
        return new UserPrincipal(user);
    }

}
