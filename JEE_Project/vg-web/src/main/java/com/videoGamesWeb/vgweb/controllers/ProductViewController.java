package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(){
        return "products";
    }
}
