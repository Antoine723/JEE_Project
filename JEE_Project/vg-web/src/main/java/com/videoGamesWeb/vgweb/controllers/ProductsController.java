package com.videoGamesWeb.vgweb.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.dto.SearchDTO;
import com.videoGamesWeb.vgcore.entity.Console;
import com.videoGamesWeb.vgcore.entity.Game;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<String> addComment(@RequestBody SearchDTO searchDTO) throws JsonProcessingException {
        logger.info("recieve {}", searchDTO.getInput());
        List<Product> results = productService.searchWithText(searchDTO.getInput())
                .stream()
                .peek(product -> {
                    product.setComments(null);
                    product.setOrders(null);
                    //set images ?
                    switch (product.getClass().getSimpleName()) {
                        case "Game":
                            ((Game) product).setConsoles(null);
                            break;
                        case "Console":
                            ((Console) product).setGames(null);
                            break;
                    }
                })
                //.sorted(Comparator.comparing(Product::getName).reversed())
                .collect(Collectors.toList());
        logger.info(String.valueOf(results.size()));
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(results);
        logger.info("result : {}", jsonString);
        return ResponseEntity.ok(jsonString);
    }
}
