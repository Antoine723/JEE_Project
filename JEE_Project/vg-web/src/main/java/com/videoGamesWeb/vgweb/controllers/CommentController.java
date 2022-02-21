package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.dto.CommentDTO;
import com.videoGamesWeb.vgcore.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }


    @PostMapping(value = "addComment")
    public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        long userId;
        try {
            userId = (long) request.getSession().getAttribute(SESSION_USER_ID);
        } catch (NullPointerException | NumberFormatException ignore) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No logged user!");
        }

        commentDTO.setUserId(userId);
        this.commentService.addComment(commentDTO);
        logger.info("Comment well added");
        return ResponseEntity.ok("Comment well added");
    }
}
