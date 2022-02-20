package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.dto.CommentDTO;
import com.videoGamesWeb.vgcore.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }


    @PostMapping(value = "addComment")
    public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDTO){
        this.commentService.addComment(commentDTO);
        logger.info("Comment well added");
        return ResponseEntity.ok("Comment well added");
    }
}
