package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.dto.CommentDTO;
import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.CommentService;
import com.videoGamesWeb.vgcore.service.ProductService;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@RestController
public class CommentController {
    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;
    private final UserService userService;
    private final ProductService productService;

    public CommentController(CommentService commentService, UserService userService, ProductService productService){
        this.commentService = commentService;
        this.userService = userService;
        this.productService = productService;
    }


    @PostMapping(value = "addComment")
    public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDTO, HttpSession session){
        long userId;
        try {
            userId = (long) session.getAttribute(SESSION_USER_ID);
        } catch (NullPointerException | NumberFormatException ignore) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No logged user!");
        }

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No logged user!");
        }

        Optional<Product> productOpt = productService.findById(commentDTO.getProductId());
        if (productOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Product invalid!");
        }

        Comment comment = new Comment();
        comment.setUser(userOpt.get());
        comment.setProduct(productOpt.get());
        comment.setRating(commentDTO.getRating());
        comment.setContent(commentDTO.getComment());

        this.commentService.addComment(comment);
        logger.info("Comment well added");
        return ResponseEntity.ok("Comment well added");
    }
}
