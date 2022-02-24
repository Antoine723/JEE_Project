package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.CommentService;
import com.videoGamesWeb.vgcore.service.ConsoleService;
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

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@Controller
@RequestMapping(value = "/product")
public class ProductViewController extends GenericController{

    private final static Logger logger = LoggerFactory.getLogger(ProductViewController.class);

    private final static String PRODUCT_PAGE = "product";

    private final ConsoleService consoleService;
    private final ProductService productService;
    private final CommentService commentService;
    private final UserService userService;

    public ProductViewController(ConsoleService consoleService,
                                 ProductService productService,
                                 CommentService commentService,
                                 UserService userService){
        this.consoleService = consoleService;
        this.productService = productService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping({"/{id}", "/{id}/{consoleGameName}"})
    public String getProduct(@PathVariable long id, @PathVariable(required = false) String consoleGameName, Model model){
        Optional<Product> productOpt = this.productService.findById(id);
        if (productOpt.isEmpty()) return "redirect:/";

        long consoleId = productOpt.get().getId();
        if (consoleGameName != null) {
            model.addAttribute("consoleGameName", consoleGameName);
            consoleId = this.consoleService.findIdByName(consoleGameName);
        }

        model.addAttribute("product", productOpt.get());
        model.addAttribute("consoleId", consoleId);
        model.addAttribute("dateFormat", new SimpleDateFormat("dd MMM yyyy"));
        return PRODUCT_PAGE;
    }

    @PostMapping("/{productId}/comment")
    public String postProductComment(@PathVariable long productId,
                                     @RequestParam String consoleUrl,
                                     @RequestParam int rating,
                                     @RequestParam String newComment,
                                     HttpSession session) {
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

        Optional<Product> productOpt = productService.findById(productId);
        if (productOpt.isEmpty()){
            return "redirect:/home";
        }

        Comment comment = new Comment();
        comment.setUser(userOpt.get());
        comment.setProduct(productOpt.get());
        comment.setRating(rating);
        comment.setContent(newComment.replaceAll("\n", "<br>"));

        this.commentService.addComment(comment);
        return "redirect:/product/"+productId+"/"+consoleUrl;
    }
}
