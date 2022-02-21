package com.videoGamesWeb.vgweb.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videoGamesWeb.vgcore.dto.SearchDTO;
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

    private final ObjectMapper mapper = new ObjectMapper();

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<String> addComment(@RequestBody SearchDTO searchDTO) throws JsonProcessingException {
        logger.info("recieve {}", searchDTO.getInput());
        List<Product> results = productService.searchWithText(searchDTO.getInput());/*
                .stream()
                .peek(product -> {
                    if (product instanceof Game) {
                        product.setImg(product.getImg()+"_"+((Game) product).getConsoles().get(0).getName());
                        logger.info("game {} => console {}", product.getName(), ((Game) product).getConsoles().get(0).getName());
                    }
                })
                .collect(Collectors.toList());*/
        //results = results.stream().sorted(Comparator.comparing(Product::getName).reversed()).collect(Collectors.toList());
        return ResponseEntity.ok(mapper.writeValueAsString(results));
    }
}
