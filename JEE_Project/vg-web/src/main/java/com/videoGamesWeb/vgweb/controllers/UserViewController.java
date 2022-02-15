package com.videoGamesWeb.vgweb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserViewController {

    private final static Logger logger = LoggerFactory.getLogger(UserViewController.class);

    @GetMapping("/create")
    public String getCreate(){
        logger.info("ViewController /user/create get");
        return "user_create";
    }

    @PostMapping("/create")
    public String PostCreate(){
        logger.info("ViewController /user/create post");
        //process post params
        if (false) return "user_create";
        return "redirect:/user/connect";
    }

    @GetMapping("/connect")
    public String getConnect(){
        logger.info("ViewController /user/connect get");
        return "user_connect";
    }

    @PostMapping("/connect")
    public String PostConnect(){
        logger.info("ViewController /user/connect post");
        //process post params
        if (false) return "user_connect";
        return "redirect:/user/profile";
    }

    @GetMapping("/disconnect")
    public String getDisconnect(){
        logger.info("ViewController /user/connect get");
        return "redirect:/user/connect";
    }

    @GetMapping("/profile")
    public String getProfile(){
        logger.info("ViewController /user/profile get");
        return "user_profile";
    }

    @GetMapping("/update")
    public String getUpdate(){
        logger.info("ViewController /user/update get");
        return "user_update";
    }

    @PostMapping("/update")
    public String postUpdate(){
        logger.info("ViewController /user/update post");
        //process post params
        if (false) return "user_update";
        return "redirect:/user/profile";
    }

    @GetMapping("/delete")
    public String getDelete(){
        logger.info("ViewController /user/delete get");
        return "redirect:/home"; // redirige vers l'accueil
    }
}
