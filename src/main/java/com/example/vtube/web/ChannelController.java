package com.example.vtube.web;

import com.example.vtube.service.ChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChannelController {
    @Autowired
    ChannelManager channelManager;
}
