package com.example.vtube.service;

import com.example.vtube.dao.entities.Role;
import com.example.vtube.dao.entities.User;
import com.example.vtube.dao.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements  UserManager {
    private final PasswordEncoder passwordEncoder;
    private static List<User> users = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void postConstruct() {
        User user = new User();
        user.setUserId(user.getUserId());
        user.setRole(Role.ADMIN);
        user.setUsername("imane");
        user.setEmail("imane@gmail.com");
        user.setPassword(passwordEncoder.encode("imane"));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public User registerUser(User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String username) {
        return userRepository.findByUsername(username).get(0);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);
        }
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username).get(0);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get(0);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveUsers(List<User> users) {
        userRepository.saveAll(users);
    }
}


