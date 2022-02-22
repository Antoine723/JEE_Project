package com.videoGamesWeb.vgcore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BasketService {

    private final static Logger logger = LoggerFactory.getLogger(BasketService.class);
    private final ProductService productService;

    public BasketService(ProductService productService){
        this.productService = productService;
    }

    public Basket getBasketFromSession(HttpSession session) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode basketSessionAttribute = (JsonNode) session.getAttribute("basket");
        if (basketSessionAttribute == null){
            return new Basket();
        } else {
            Basket basket = mapper.treeToValue(basketSessionAttribute, Basket.class);
            basket.setProductsAndQuantities(this.getBasketContent(basket));
            return basket;
        }
    }

    public void writeBasketInSession(HttpSession session, Basket basket) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.valueToTree(basket);
        session.setAttribute("basket", json);
    }

    private Map<Product, Integer> getBasketContent(Basket basket){
        Map<Product, Integer> map = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : basket.getProductsIdAndQuantities().entrySet()){
            Optional<Product> productOpt = this.productService.findById(entry.getKey());
            if (productOpt.isPresent()){
                map.put(productOpt.get(), entry.getValue());
            }
        }
        return map;
    }
}
