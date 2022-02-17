package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.service.GameService;
import com.videoGamesWeb.vgcore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductViewController {

    private final ProductService productService;
    private final static Logger logger = LoggerFactory.getLogger(ProductViewController.class);

    public ProductViewController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", this.productService.findAll());
        return "products";
    }
}
