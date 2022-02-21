package com.videoGamesWeb.vgweb.controllers;


import com.videoGamesWeb.vgcore.dto.SearchDTO;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<String> addComment(@RequestBody SearchDTO searchDTO){
        logger.info("recieve {}", searchDTO.getInput());
        List<Product> results = productService.searchWithText(searchDTO.getInput());
        logger.info(String.valueOf(results.size()));
        return ResponseEntity.ok(results.toString());
    }
}
