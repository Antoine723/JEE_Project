package com.videoGamesWeb.vgweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.CommentService;
import com.videoGamesWeb.vgcore.service.ProductService;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_BASKET;
import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@Controller
public class ProductViewController extends GenericController{

    private final static Logger logger = LoggerFactory.getLogger(ProductViewController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ProductService productService;
    private final CommentService commentService;
    private final UserService userService;

    public ProductViewController(ProductService productService, CommentService commentService, UserService userService){
        this.productService = productService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping(value = {"/product/{id}", "/product/{id}/{consoleGameName}"})
    public String getProduct(@PathVariable long id, @PathVariable(required = false) String consoleGameName, Model model){
        Optional<Product> productOpt = this.productService.findById(id);
        if (productOpt.isEmpty()){
            model.addAttribute("productNotFound", true);
            return "product";
        }
        if (consoleGameName != null){
            model.addAttribute("consoleGameName", consoleGameName);
        }
        Product product = productOpt.get();
        model.addAttribute("product", product);
        model.addAttribute("dateFormat", new SimpleDateFormat("dd MMM yyyy"));
        model.addAttribute("prefix", this.prefix);
        return "product";
    }

    @PostMapping("/product/{id}/comment")
    public String postProductComment(@PathVariable long id,
                                     @RequestParam String consoleUrl,
                                     @RequestParam int rating,
                                     @RequestParam String newComment,
                                     HttpSession session) {
        logger.info("recieve {} {} {} {}", id, consoleUrl, rating, newComment);
        long userId;
        try {
            userId = (long) session.getAttribute(SESSION_USER_ID);
        } catch (NullPointerException | NumberFormatException ignore) {
            return "redirect:/user/connect";
        }

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return "redirect:/user/disconnect";
        }

        Optional<Product> productOpt = productService.findById(id);
        if (productOpt.isEmpty()){
            return "redirect:/home";
        }

        Comment comment = new Comment();
        comment.setUser(userOpt.get());
        comment.setProduct(productOpt.get());
        comment.setRating(rating);
        comment.setContent(newComment.replaceAll("\n", "<br>"));

        this.commentService.addComment(comment);
        return "redirect:/product/"+id+"/"+consoleUrl;
    }

    @PostMapping("/product/{id}/basket")
    public String postProductBasket(@PathVariable long id,
                                    @RequestParam String consoleUrl,
                                    @RequestParam int quantity,
                                    HttpSession session) throws JsonProcessingException {
        if (!UserViewController.userInSession(session)) return "redirect:/user/profile";

        Optional<Product> productOpt = this.productService.findById(id);
        if (productOpt.isEmpty()) return "redirect:/home";

        JsonNode json_basket = (JsonNode) session.getAttribute(SESSION_BASKET);
        Basket basket = json_basket == null ? new Basket() : objectMapper.treeToValue(json_basket, Basket.class);

        basket.addProduct(id, quantity);

        session.setAttribute(SESSION_BASKET, objectMapper.valueToTree(basket));

        logger.info("Well added to basket");
        return "redirect:/product/"+id+"/"+consoleUrl;
    }
}
