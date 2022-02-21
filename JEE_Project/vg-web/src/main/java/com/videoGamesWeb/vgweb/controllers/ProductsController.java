package com.videoGamesWeb.vgweb.controllers;


import com.videoGamesWeb.vgcore.dto.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {
    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @PostMapping(value = "/search")
    public ResponseEntity<String> addComment(@RequestBody SearchDTO searchDTO){
        logger.info("recieve {}", searchDTO.getInput());
        return ResponseEntity.ok("Résultats de la recherche "+searchDTO.getInput());
    }
}
