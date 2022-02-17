package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserViewController {

    private final static Logger logger = LoggerFactory.getLogger(UserViewController.class);

    private final UserService userService;

    public UserViewController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getCreate(){
        logger.info("ViewController /user/create get");
        return "user_create";
    }

    @PostMapping("/create")
    public String PostCreate(@RequestParam String name,
                             @RequestParam String password,
                             @RequestParam String confirm_password,
                             @RequestParam(required=false) String mail,
                             @RequestParam(required=false) String address){
        logger.info("ViewController /user/create post");
        //process post params : verify if not null
        if (false) return "user_create";
        return "redirect:/user/connect";
    }

    @GetMapping("/connect")
    public String getConnect(){
        logger.info("ViewController /user/connect get");
        return "user_connect";
    }

    @PostMapping("/connect")
    public String PostConnect(@RequestParam String name,
                              @RequestParam String password) {
        logger.info("ViewController /user/connect post");
        logger.info("get "+name+" and "+password);
        //process post params
        if (false) return "user_connect";
        return "redirect:/user/profile/1";
    }

    @GetMapping("/disconnect/{userId}")
    public String getDisconnect(@PathVariable long userId){
        logger.info("ViewController /user/connect get");
        return "redirect:/user/connect";
    }

    @GetMapping("/profile/{userId}")
    public String getProfile(@PathVariable long userId){
        logger.info("ViewController /user/profile get");
        return "user_profile";
    }

    @GetMapping("/update/{userId}")
    public String getUpdate(@PathVariable long userId){
        logger.info("ViewController /user/update get");
        return "user_update";
    }

    @PostMapping("/update/{userId}")
    public String postUpdate(@PathVariable long userId){
        logger.info("ViewController /user/update post");
        //process post params
        if (false) return "user_update";
        return "redirect:/user/profile/1";
    }

    @GetMapping("/delete/{userId}")
    public String getDelete(@PathVariable long userId){
        logger.info("ViewController /user/delete get");
        if (this.userService.existById(userId)) this.userService.deleteById(userId);
        return "redirect:/";
    }
}
