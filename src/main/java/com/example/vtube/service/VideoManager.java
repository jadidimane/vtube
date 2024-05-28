package com.example.vtube.service;

import com.example.vtube.dao.entities.Channel;
import com.example.vtube.dao.entities.User;
import com.example.vtube.dao.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoManager {
    public Video addVideo(String title, String description, MultipartFile file, MultipartFile file2 , User user, Channel channel) throws IOException;
    public Page<Video> getAllVideos(int page, int taille);
    public Video getVideoById(Integer videoId);
    public Page<Video> getVideosByUploader(User uploader, int page, int taille);
    public Page<Video> findByKeyword(String keyword,int page, int taille);
    public int incrementViews(Video video);
    public void incrementLikes(Video video);
    public void incrementDislikes(Video video);
    public void decrementLikes(Video video);
    public void decrementDislikes(Video video);
    public Video updateVideo(Video video, MultipartFile fileImage,MultipartFile videoFile) throws IOException;
    public Video updateVideo(Video video);
    public void delete(Integer id);
    public List<Video> listVideos();
}
