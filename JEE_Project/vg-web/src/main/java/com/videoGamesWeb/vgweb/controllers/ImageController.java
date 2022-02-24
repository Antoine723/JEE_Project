package com.videoGamesWeb.vgweb.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping( "/image")
public class ImageController {

    @GetMapping("/{imgName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imgName){
        ClassPathResource imgFile = new ClassPathResource("/images/" + imgName + ".png");
        byte[] bytes = new byte[0];
        try {
            bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);

    }
}
