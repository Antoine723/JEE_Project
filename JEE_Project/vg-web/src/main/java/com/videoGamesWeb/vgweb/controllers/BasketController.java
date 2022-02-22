package com.videoGamesWeb.vgweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.dto.BasketDTO;
import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.service.BasketService;
import com.videoGamesWeb.vgcore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class BasketController {

    private final static Logger logger = LoggerFactory.getLogger(BasketController.class);
    private final ProductService productService;
    private final BasketService basketService;

    public BasketController(ProductService productService, BasketService basketService){
        this.productService = productService;
        this.basketService = basketService;
    }

    @PostMapping("addToBasket")
    public ResponseEntity<String> addToBasket(@RequestBody BasketDTO basketDTO, HttpSession session) throws JsonProcessingException {
        Basket basket = this.basketService.getBasketFromSession(session);
        Optional<Product> productOpt = this.productService.findById(basketDTO.getProductId());
        if (productOpt.isPresent()){
            basket.addProduct(productOpt.get(), basketDTO.getQuantity());
        }
        this.basketService.writeBasketInSession(session, basket);
        logger.info("Well added to basket");
        return ResponseEntity.ok("Well added to basket");
    }
}
