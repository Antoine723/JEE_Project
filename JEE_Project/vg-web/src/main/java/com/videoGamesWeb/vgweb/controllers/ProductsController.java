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

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
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
        logger.info("recieve {}, {}, {}", searchDTO.getInput(), searchDTO.getSort_by(), searchDTO.getSort_asc());
        List<Product> results = productService.searchWithText(searchDTO.getInput());

        Comparator<Product> comparator = null;
        switch(searchDTO.getSort_by()) {
            case "name":
                comparator = Comparator.comparing(Product::getName);
                break;
            case "score":
                logger.info("sort by score");
                //comparator = Comparator.nullsLast(Comparator.comparing(Product::getRating));
                break;
            case "price":
                comparator = Comparator.comparing(Product::getPrice);
                break;
            default:
                logger.error("got unknown sort comparator {}", searchDTO.getSort_by());
        }
        if (comparator != null) {
            if (!Boolean.parseBoolean(searchDTO.getSort_asc())) {
                comparator = comparator.reversed();
            }
            results = results.stream().sorted(comparator).collect(Collectors.toList());
        }

        return ResponseEntity.ok(mapper.writeValueAsString(results));
    }
}
