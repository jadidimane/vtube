package com.example.vtube.service;

import com.example.vtube.dao.entities.Channel;
import com.example.vtube.dao.entities.User;
import com.example.vtube.dao.entities.Video;
import com.example.vtube.dao.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService implements ChannelManager {
    @Autowired
    private ChannelRepository channelRepository;
    @Override
    public List<User> getAllSubscribers(Channel channel, int page, int taille) {
        return channel.getSubscribers();
    }

    @Override
    public Channel createChannel(User creator) {
        Channel channel1=new Channel();channel1.setUser(creator);
        return channelRepository.save(channel1);
    }

    @Override
    public Channel joinToChannel(Video video) {
        Channel channel1=new Channel();
        channel1.getVideos().add(video);
        channel1.setVideos(channel1.getVideos());
        return channelRepository.save(channel1) ;
    }

    @Override
    public Channel subscribeChannel(User subscriber) {
        Channel channel1=new Channel();
        channel1.getSubscribers().add(subscriber);
        channel1.setSubscribers(channel1.getSubscribers());
        return channelRepository.save(channel1);
    }

    @Override
    public Channel removeFromChannel(Video video) {
        Channel channel1=new Channel();
        if(channel1.getVideos().remove(video)){
            channel1.setVideos(channel1.getVideos());
            return channelRepository.save(channel1);
        }
        else {
            return null;
        }
    }
    public Channel unsubscribeChannel(User user) {
        Channel channel1 = new Channel();
        if (channel1.getSubscribers().remove(user)) {
            channel1.setSubscribers(channel1.getSubscribers());
            return channelRepository.save(channel1);
        } else {
            return null;
        }
    }
    @Override
    public Channel channel(User user){
        return channelRepository.findByUser(user)  ;  }

}
