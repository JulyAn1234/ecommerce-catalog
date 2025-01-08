package com.ecommerceCatalog.ecommerceCatalog.service;

import com.ecommerceCatalog.ecommerceCatalog.model.entities.User;
import com.ecommerceCatalog.ecommerceCatalog.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByID(String username) {
        return userRepository.getUserByUsername(username);
    }

    public Optional<User> createUser(User user) {
        System.out.println("trad");
        System.out.println(user);
        userRepository.save(user);
        return Optional.of(user);
    }
}