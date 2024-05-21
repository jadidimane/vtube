package com.example.vtube.service;

import com.example.vtube.dao.entities.Comment;
import com.example.vtube.dao.entities.Video;

import java.util.List;

public interface CommentManager {
    public Comment addComment(Comment comment);
    public List<Comment> getCommentsForVideo(Video video);

}
