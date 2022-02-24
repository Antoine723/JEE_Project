package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductService productService;

    public BasketService(ProductService productService){
        this.productService = productService;
    }

    public List<Product> getProductsList(Basket basket){
        return this.productService.findAllById(basket.getQtyByProduct().keySet().stream().collect(Collectors.toList()));
    }
}
