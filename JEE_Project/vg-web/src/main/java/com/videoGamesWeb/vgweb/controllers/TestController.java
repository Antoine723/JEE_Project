package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.entity.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test")
    public String test(){
        Comment comment = new Comment();
        logger.info("fsldfsdf");
        return "on test là " + comment;
    }
}
