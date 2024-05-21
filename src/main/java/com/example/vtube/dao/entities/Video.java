package com.example.vtube.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime uploadDate;
    private int views;
    private int likes;
    private int dislikes;
    private String thumbnailFile;
    private String videoFile;

    @OneToMany(mappedBy = "video")
    List<Comment> comments=new ArrayList<Comment>();
    @ManyToOne
    private User uploader;
    @ManyToOne
    private Channel channel;
    private String videoName;
}

