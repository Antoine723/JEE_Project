package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.service.ConsoleService;
import com.videoGamesWeb.vgcore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeViewController extends GenericController{

    private static final Logger logger = LoggerFactory.getLogger(HomeViewController.class);

    private final ProductService productService;
    private final ConsoleService consoleService;

    public HomeViewController(ProductService productService, ConsoleService consoleService) {
        this.productService = productService;
        this.consoleService = consoleService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("price_min", productService.getPriceMin());
        model.addAttribute("price_max", productService.getPriceMax());
        model.addAttribute("console_names", consoleService.getNames());
        model.addAttribute("prefix", this.prefix);
        return "home";
    }
}
