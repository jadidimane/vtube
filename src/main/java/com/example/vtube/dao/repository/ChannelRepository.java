package com.example.vtube.dao.repository;

import com.example.vtube.dao.entities.Channel;
import com.example.vtube.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

  public interface ChannelRepository extends JpaRepository<Channel,Integer> {
    Channel findByUser(User user);
}

