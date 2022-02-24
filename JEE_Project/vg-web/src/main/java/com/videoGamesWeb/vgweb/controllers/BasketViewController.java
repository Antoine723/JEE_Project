package com.videoGamesWeb.vgweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.service.BasketService;
import com.videoGamesWeb.vgcore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.videoGamesWeb.vgweb.VgWebApplication.SESSION_USER_ID;

@Controller
public class BasketViewController extends GenericController{

    private final static Logger logger = LoggerFactory.getLogger(BasketViewController.class);
    private final BasketService basketService;
    private final UserService userService;

    public BasketViewController(BasketService basketService, UserService userService){
        this.basketService = basketService;
        this.userService = userService;
    }

    @GetMapping("basket")
    public String basket(Model model, HttpSession session) throws JsonProcessingException {
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

        Basket basket = this.basketService.getBasketFromSession(session);
        model.addAttribute("basket", basket);
        model.addAttribute("prefix", this.prefix);
        model.addAttribute("totalAmount", basket.computeTotalAmount());
        return "basket";
    }
}
