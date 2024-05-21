package com.example.vtube.web;

import com.example.vtube.dao.entities.Channel;
import com.example.vtube.dao.entities.Comment;
import com.example.vtube.dao.entities.User;
import com.example.vtube.dao.entities.Video;
import com.example.vtube.service.ChannelManager;
import com.example.vtube.service.CommentManager;
import com.example.vtube.service.UserManager;
import com.example.vtube.service.VideoManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class VideoController {
    @Autowired
    VideoManager videoManager;
    @Autowired
    CommentManager commentManager;
    @Autowired
    UserManager userManager;
    @Autowired
    ChannelManager channelManager;
    @GetMapping("/")
    public String start(Model model) {
        return "index";
    }
    @GetMapping("/index")
    public String listVideo(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "3") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword
    ) {
        Page<Video> videos = videoManager.getAllVideos(page, taille);
        model.addAttribute("listVideos", videos.getContent());
        int[] pages = new int[videos.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        videos.forEach(video->System.out.println(video.getThumbnailFile()));
        return "index";
    }
    @GetMapping("/manage")
    public String Videolist(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "taille", defaultValue = "3") int taille,
                            @RequestParam(name = "search", defaultValue = "") String keyword,HttpSession httpSession
    ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user= userManager.getUserByUsername(username);
        Page<Video> videos = videoManager.getVideosByUploader(user,page,taille);
        model.addAttribute("listVideos", videos.getContent());
        int[] pages = new int[videos.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        videos.forEach(video->System.out.println(video.getThumbnailFile()));
        return "manageVideosUser";}
        else{
            return "";
        }
    }

    @GetMapping("playvideo")
    public String playvideo(Model model,@RequestParam(name = "id") Integer id) {
        Video video=videoManager.getVideoById(id);
        model.addAttribute("video",video);
        model.addAttribute("id",id);
        return "playvideo";
    }
    @GetMapping("/upload")
    public String uploadform(Model model) {
        return "upload";
    }

    @PostMapping("addVideo")
    public String Videonadd(@RequestParam("t") String title, @RequestParam("d") String description, @RequestParam("thumbnailFile")MultipartFile file , @RequestParam("videoFile")MultipartFile file2, HttpSession httpSession) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user= userManager.getUserByUsername(username);
            try{
                Channel channel=channelManager.createChannel(user);
                Video video = videoManager.addVideo(title, description,file,file2,user,channel);
                Channel channem1=channelManager.joinToChannel(video);
                return "manageVideosUser";
            } catch(IOException e){
                System.out.println(e);
                return "error";
            }}else {
            return "";
        }


    }}
    /*@PostMapping("addcomment")
    public String addComment(Model model, @RequestParam("content")String contenu,@RequestParam(name = "id") Integer id){
        Comment comment=new Comment();
        comment.setCommentText("content");
        comment.setCommentDate(LocalDateTime.now());
        Video video=new Video();
        comment.setVideo(videoManager.getVideoById(id));
        commentManager.addComment(comment);

        return "redirect:playvideo";
    }*/
   /* @GetMapping("/drag")
    public String template(Model model){
        return "error";
    }*/
//    @PostMapping("incrementlikes")
//    public  String incrementlikes(Model model){
//        commentManager.+
//    }

