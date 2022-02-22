package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.service.ConsoleService;
import com.videoGamesWeb.vgcore.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsViewController extends GenericController{

    private final ConsoleService consoleService;
    private final GameService gameService;
    private final static Logger logger = LoggerFactory.getLogger(ProductsViewController.class);

    public ProductsViewController(ConsoleService consoleService, GameService gameService){
        this.consoleService = consoleService;
        this.gameService = gameService;
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("consoles", this.consoleService.findAll());
        model.addAttribute("games", this.gameService.findAll());
        model.addAttribute("prefix", this.prefix);
        return "products";
    }
}
