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
public class Channel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ChannelId;
    @ManyToMany
    private List<User> subscribers=new ArrayList<User>();
    @OneToMany(mappedBy = "channel", cascade = CascadeType.REMOVE)
    List<Video> videos=new ArrayList<Video>();
    @OneToOne(mappedBy = "channel",cascade = CascadeType.REMOVE)
    private User user;
    private String name;
}

