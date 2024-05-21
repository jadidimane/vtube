package com.example.vtube.service;

import com.example.vtube.dao.entities.Channel;
import com.example.vtube.dao.entities.User;
import com.example.vtube.dao.entities.Video;

import java.util.List;

public interface ChannelManager {
    List<User> getAllSubscribers(Channel channel, int page , int taille);
    Channel createChannel(User creator);
    Channel joinToChannel(Video video);
    Channel subscribeChannel(User subscriber);

    Channel removeFromChannel(Video video);
    Channel channel(User user);


}
