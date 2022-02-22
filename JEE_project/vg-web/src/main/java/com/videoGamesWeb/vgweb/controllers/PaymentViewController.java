package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PaymentViewController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentViewController.class);
    private final UserService userService;

    public PaymentViewController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/payment")
    public String payment(Model model, HttpSession session){
        User user = this.userService.getById((long) session.getAttribute("userID"));
        model.addAttribute("user", user);
        return "payment";
    }
}
