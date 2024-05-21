package com.example.vtube.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;
    private String commentText;
    private LocalDateTime commentDate;
    private int likes;
    private int dislikes;
    @ManyToOne
    private Video video;
    @ManyToOne
    private User commenters;
}
