package com.videoGamesWeb.vgweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.service.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BasketViewController extends GenericController{

    private final BasketService basketService;
    private final static Logger logger = LoggerFactory.getLogger(BasketViewController.class);

    public BasketViewController(BasketService basketService){
        this.basketService = basketService;
    }

    @GetMapping("basket")
    public String basket(Model model, HttpSession session) throws JsonProcessingException {
        Basket basket = this.basketService.getBasketFromSession(session);
        model.addAttribute("basket", basket);
        model.addAttribute("prefix", this.prefix);
        model.addAttribute("totalAmount", basket.computeTotalAmount());
        return "basket";
    }
}
