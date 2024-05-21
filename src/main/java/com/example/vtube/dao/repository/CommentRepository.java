package com.example.vtube.dao.repository;

import com.example.vtube.dao.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
