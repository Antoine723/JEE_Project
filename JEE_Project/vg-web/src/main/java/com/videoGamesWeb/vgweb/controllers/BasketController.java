package com.videoGamesWeb.vgweb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.dto.BasketDTO;
import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Product;
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

    public BasketController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("addToBasket")
    public ResponseEntity<String> addToBasket(@RequestBody BasketDTO basketDTO, HttpSession session) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String basketSessionAttribute = (String) session.getAttribute("basket");
        Basket basket;
        if (basketSessionAttribute == null){
            basket = new Basket();
        } else {
            basket = mapper.readValue((String) basketSessionAttribute,Basket.class);
        }
        Optional<Product> productOpt = this.productService.findById(basketDTO.getProductId());
        if (productOpt.isPresent()){
            basket.addProduct(productOpt.get(), basketDTO.getQuantity());
        }
        String json = mapper.writeValueAsString(basket);
        session.setAttribute("basket", json);
        logger.info("Well added to basket");
        return ResponseEntity.ok("Well added to basket");
    }
}
