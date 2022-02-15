package com.videoGamesWeb.vgweb.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    private static final Logger logger = LoggerFactory.getLogger(HomeViewController.class);

    @GetMapping("/")
    public String home(Model model){
        logger.info("HOOOME");
        return "home";
    }
}
