package com.example.vtube.service;

import com.example.vtube.dao.entities.Channel;
import com.example.vtube.dao.entities.User;
import com.example.vtube.dao.entities.Video;
import com.example.vtube.dao.repository.ChannelRepository;
import com.example.vtube.dao.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class VideoService implements VideoManager {
    private String FOLDER_PATH="C:\\Users\\imane\\vtube\\src\\main\\resources\\static\\localimages\\";
    private String FOLDER_PATH2="C:\\Users\\imane\\vtube\\src\\main\\resources\\static\\videos\\";
    private String ROOT_PATH="C:\\Users\\imane\\vtube\\src\\main\\resources\\static\\";
    @Autowired
    VideoRepository videoRepository;
    ChannelRepository channelRepository;

    @Override
     public Video addVideo(String title, String description, MultipartFile fileImage, MultipartFile videoFile,User user,Channel channel) throws IOException {
        Video video = new Video();
        video.setDescription(description);
        video.setTitle(title);
        video.setUploader(user);
        Video video1=videoRepository.save(video);
        video1.setThumbnailFile(convertToRelativePath(uploadImageToFileSystem(fileImage,video1.getId()),ROOT_PATH));
        video1.setVideoFile(convertToRelativePath(uploadVideoToFileSystem(videoFile,video1.getId()),ROOT_PATH));
        video1.setChannel(channel);
        return videoRepository.save(video1);
    }


    @Override
    public Page<Video> getAllVideos(int page, int taille) {

        return videoRepository.findAll(PageRequest.of(page,taille));
    }

    @Override
    public Video getVideoById(Integer videoId) {
        return videoRepository.findById(videoId).get();
    }

    @Override
    public Page<Video> getVideosByUploader(User uploader, int page, int taille) {
        return videoRepository.findByUploader(uploader, PageRequest.of(page, taille));
    }

    @Override
    public Page<Video> findByKeyword(String keyword, int page, int taille) {
        return videoRepository.findByTitleContains(keyword, PageRequest.of(page, taille));
    }

    @Override
    public void incrementViews(Video video) {
        video.setViews(video.getViews() + 1);
    }

    @Override
    public void incrementLikes(Video video) {
        video.setLikes(video.getLikes() + 1);
    }

    @Override
    public void incrementDislikes(Video video) {
        video.setDislikes(video.getDislikes() + 1);

    }

    @Override
    public void decrementLikes(Video video) {
        video.setLikes(video.getLikes() - 1);
    }

    @Override
    public void decrementDislikes(Video video) {
        video.setDislikes(video.getDislikes() - 1);
    }

    public byte[] downloadVideoFromFileSystem(String filename) throws IOException {
        Video video = videoRepository.findByVideoName(filename);
        String filePath = video.getVideoFile();
        return Files.readAllBytes(new File(filePath).toPath());
    }
    public String uploadImageToFileSystem(MultipartFile file,int id) throws IOException {
        String filePath=FOLDER_PATH+id+file.getOriginalFilename();
         Video video=videoRepository.save(Video.builder()
                .thumbnailFile(filePath).build());

        file.transferTo(new File(filePath));

        if (video.getThumbnailFile()!= null) {
            return filePath;
        }
        return null;
    }
    public String uploadVideoToFileSystem(MultipartFile file,int id) throws IOException {
        String filePath=FOLDER_PATH2+id+file.getOriginalFilename();
        Video video=videoRepository.save(Video.builder()
                .thumbnailFile(filePath).build());

        file.transferTo(new File(filePath));

        if (video.getThumbnailFile()!= null) {
            return filePath;
        }
        return null;
    }

    private static String convertToRelativePath(String absolutePath, String rootDirectory) {
        absolutePath = absolutePath.replace("\\", "/");
        rootDirectory = rootDirectory.replace("\\", "/");

        // Ensure root directory ends with a "/"
        if (!rootDirectory.endsWith("/")) {
            rootDirectory += "/";
        }

        // Check if the absolute path starts with the root directory
        if (absolutePath.startsWith(rootDirectory)) {
            // Remove the root directory from the absolute path
            return absolutePath.substring(rootDirectory.length());
        } else {
            // Return the absolute path unchanged if it doesn't start with the root directory
            return absolutePath;
        }
    }
}
