package com.videoGamesWeb.vgweb.controllers;

import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductViewController extends GenericController{

    private final ProductService productService;

    public ProductViewController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = {"/product/{id}", "/product/{id}/{consoleGameName}"})
    public String product(@PathVariable long id, @PathVariable(required = false) String consoleGameName, Model model){
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
        model.addAttribute("prefix", this.prefix);
        return "product";
    }
}
