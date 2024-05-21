package com.example.vtube.service;

import com.example.vtube.dao.entities.User;

import java.util.List;

public interface UserManager {
    public void registerUser(User user);
    public User findByLogin(String username);
    public void updateUser(User user);
    public List<User> getAllUsers();
    public boolean authenticateUser(String username, String password);
    public User getUserById(Integer id);
    public void deleteUser(Integer id);
    public void createUser(User user);
    public User getUserByUsername(String username);
}
