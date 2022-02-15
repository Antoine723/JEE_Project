package com.videoGamesWeb.vgweb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    private final static Logger logger = LoggerFactory.getLogger(HomeViewController.class);

    @GetMapping("/home")
    public String test(){
        logger.info("ViewController /home");
        return "home";
    }
}
