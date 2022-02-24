package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@Controller
@RequestMapping( "/payment")
public class PaymentViewController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentViewController.class);
    private final UserService userService;

    public PaymentViewController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public String getPage(Model model, HttpSession session){
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

        model.addAttribute("user", userOpt.get());
        return "payment";
    }
}
