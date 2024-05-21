package com.example.vtube.service;

import com.example.vtube.dao.entities.Comment;
import com.example.vtube.dao.entities.Video;
import com.example.vtube.dao.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService implements CommentManager{
    @Autowired
    CommentRepository commentRepository;
    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsForVideo(Video video) {
        return video.getComments();
    }
}
