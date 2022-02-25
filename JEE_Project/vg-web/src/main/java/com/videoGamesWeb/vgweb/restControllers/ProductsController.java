package com.videoGamesWeb.vgweb.restControllers;


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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/search")
    public ResponseEntity<String> postSearch(@RequestBody SearchDTO searchDTO) throws JsonProcessingException {
        List<Product> results;
        if (searchDTO.getConsoles().isEmpty()) {
            results = productService.searchWithText(searchDTO.getInput(),
                    searchDTO.getMin_price(), searchDTO.getMax_price(),
                    searchDTO.getMin_score());
        }
        else {
            results = productService.searchWithTextAndConsoles(searchDTO.getInput(),
                    searchDTO.getMin_price(), searchDTO.getMax_price(),
                    searchDTO.getMin_score(),
                    searchDTO.getConsoles());
        }
        boolean sort_asc = Boolean.parseBoolean(searchDTO.getSort_asc());

        Comparator<Product> comparator = null;
        switch(searchDTO.getSort_by()) {
            case "name":
                comparator = Comparator.comparing(Product::getName);
                break;
            case "score":
                comparator = Comparator.comparing(Product::getRating, sort_asc ?
                        Comparator.nullsLast(Comparator.naturalOrder()) : Comparator.nullsFirst(Comparator.naturalOrder()));
                break;
            case "price":
                comparator = Comparator.comparing(Product::getPrice);
                break;
            default:
                logger.error("got unknown sort comparator {}", searchDTO.getSort_by());
        }
        if (comparator != null) {
            if (!sort_asc) {
                comparator = comparator.reversed();
            }
            results = results.stream().sorted(comparator).collect(Collectors.toList());
        }

        Game.minPrice = searchDTO.getMin_price();
        Game.maxPrice = searchDTO.getMax_price();

        return ResponseEntity.ok(mapper.writeValueAsString(results));
    }
}
