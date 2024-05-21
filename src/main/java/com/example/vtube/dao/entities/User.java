package com.example.vtube.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId ;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    @OneToOne
    private Channel channel;
    @OneToMany(mappedBy = "uploader")
    List<Video> videos=new ArrayList<Video>();
    @ManyToMany(mappedBy = "subscribers")
    List<Channel> subscribedChannels=new ArrayList<Channel>();
}
