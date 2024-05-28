package com.example.vtube.web;

import ch.qos.logback.core.model.Model;
import com.example.vtube.dao.entities.User;
import com.example.vtube.service.ChannelManager;
import com.example.vtube.service.UserManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {
    @Autowired
    VideoController videoController;
    @Autowired
    UserManager userManager;
    @Autowired
    ChannelManager channelManager;
    @GetMapping("/")
    public String start(org.springframework.ui.Model model) {
        return "redirect:register";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String getLoginPage(org.springframework.ui.Model model) {
        return "login_page";
    }
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }
    @PostMapping("/registerdb")
    public String registerdb(Model model, @RequestParam(name="username")String username , @RequestParam(name="email")String name, @RequestParam(name="password")String password, @RequestParam(name="password2")String password2){
        if(password.equals(password2)){
            User user=new User();
            user.setEmail(name);
            user.setUsername(username);
            user.setPassword(password);
            User user1=userManager.registerUser(user);
            channelManager.createChannel(user1);
            System.out.println(user1.toString());
            return "redirect:/login";
        }
        else {
            return "redirect:register";
        }
    }
}
