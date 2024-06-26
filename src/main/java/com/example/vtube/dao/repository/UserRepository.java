package com.example.vtube.dao.repository;

import com.example.vtube.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByUsername(String username);

}

