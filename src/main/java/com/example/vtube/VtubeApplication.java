package com.example.vtube;

import com.example.vtube.dao.repository.UserRepository;
import com.example.vtube.service.ChannelManager;
import com.example.vtube.service.UserManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VtubeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VtubeApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(UserRepository userRepository, ChannelManager channelManager, UserManager userManager) {
        return args -> {

          /*  Video video=new Video();
            Video video1=videoManager.addVideo("how to build an e-commerce website from scratch","its a video tutorial");
            Video video2=videoManager.addVideo("how to build an book-Store website from scratch","its a video tutorial");
            Video video3=videoManager.addVideo("how to build an livraison website from scratch","its a video tutorial");
            Video video4=videoManager.addVideo("how to build an e-commerce website from scratch","its a video tutorial");
            Video video5=videoManager.addVideo("how to build an e-commerce website from scratch","its a video tutorial");

            User user=new User();
            user.setEmail("imane@gmail.com");
            user.setPassword("12345");
            User user1=userManager.addUser(user);
            Channel channel=new Channel();

            channel.setName("easy tutorial");
            channel.setVideos(List.of(video1,video2,video3,video4,video5));
            channelManager.createChannel(user1);

*/
        };
    }
}
