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
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

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

    @GetMapping("/index")
    public String listVideo(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "taille", defaultValue = "3") int taille,
                            @RequestParam(name = "search", defaultValue = "") String keyword,HttpSession httpSession
    ) { Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            User user = userManager.getUserByUsername(username);
        Page<Video> videos = videoManager.findByKeyword(keyword,page, taille);

        model.addAttribute("listVideos", videos.getContent());
        model.addAttribute("videos", videos);
        model.addAttribute("username" ,username);
        return "index";   }
        else
            return "";


    }

    @GetMapping("/manage")
    public String manage(Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "taille", defaultValue = "5") int taille,
                         @RequestParam(name = "search", defaultValue = "") String keyword, HttpSession httpSession
    ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userManager.getUserByUsername(username);
            Page<Video> videos = videoManager.getVideosByUploader(user, page, taille);
            model.addAttribute("listVideos", videos.getContent());
            int[] pages = new int[videos.getTotalPages()];
            model.addAttribute("pages", pages);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            model.addAttribute("username",username);

            return "manageVideosUser";
        } else {
            return "";
        }
    }

    @GetMapping("/playvideo")
    public String playvideo(Model model, @RequestParam(name = "id") Integer id,HttpSession httpSession) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
        Video video = videoManager.getVideoById(id);
        List<Video> videos=videoManager.listVideos();
        model.addAttribute("video", video);
        model.addAttribute("id", id);
        List<Comment> comments= commentManager.getCommentsForVideo(video);
        int views=videoManager.incrementViews(video);
        model.addAttribute("views",views);
        model.addAttribute("comments",comments);
        model.addAttribute("comments",comments);
        model.addAttribute("username",username);
        model.addAttribute("videos", videos);
        return "playvideo";}
        else
            return "";
    }

    @GetMapping("/upload")
    public String uploadform(Model model) {
        return "upload";
    }

    @PostMapping("addVideo")
    public String Videonadd(@RequestParam("t") String title, @RequestParam("d") String description, @RequestParam("thumbnailFile") MultipartFile file, @RequestParam("videoFile") MultipartFile file2, HttpSession httpSession) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userManager.getUserByUsername(username);
            try {
                Video video = videoManager.addVideo(title, description, file, file2, user, user.getChannel());
                Channel channem1 = channelManager.joinToChannel(video);
                System.out.println(video.getThumbnailFile());
                return "redirect:manage";
            } catch (IOException e) {
                System.out.println(e);
                return "error";
            }
        }
        return title;
    }

    @GetMapping("/addcomment")
    public String addComment(Model model, @RequestParam("content") String contenu, @RequestParam(name = "id") Integer id, HttpSession httpSession) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userManager.getUserByUsername(username);
            Comment comment = new Comment();
            comment.setCommentText(contenu);
            comment.setCommentDate(LocalDate.now());
            comment.setCommenters(user);
            Video video = new Video();
            comment.setVideo(videoManager.getVideoById(id));
            commentManager.addComment(comment);
            return "redirect:/playvideo?id=" + id ;
        }
        return "";
    }
    @GetMapping("/edit")
    public String updatevideo(@RequestParam(name="id")Integer id, Model model){
        Video video=videoManager.getVideoById(id);
        model.addAttribute("video",video);
        return "updateVideo";
    }
    @PostMapping("edit")
    public String updateVideo(Model model,@RequestParam("t") String title, @RequestParam("d") String description, @RequestParam("thumbnailFile") MultipartFile file, @RequestParam("videoFile") MultipartFile file2, @RequestParam("id")Integer id){
        try{
        Video video=videoManager.getVideoById(id);
        video.setDescription(description);
        video.setTitle(title);
        videoManager.updateVideo(videoManager.updateVideo(video),file,file2);
            return "redirect:manage";}
        catch (Exception e){
            return "null";
        }
    }
    @GetMapping("/delete")
    public String deleteVid(Model model,@RequestParam("id")Integer id){
        videoManager.delete(id);
        return "redirect:manage";
    }


}





   /* @GetMapping("/drag")
    public String template(Model model){
        return "error";
    }*/
//    @PostMapping("incrementlikes")
//    public  String incrementlikes(Model model){
//        commentManager.+
//    }

