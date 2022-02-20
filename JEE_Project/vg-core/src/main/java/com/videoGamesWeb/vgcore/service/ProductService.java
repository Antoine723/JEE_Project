package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return this.productRepository.findAll();
    }

    public Optional<Product> findById(long id){
        return this.productRepository.findById(id);
    }

    public float getRatingAverage(Product product){
        List<Comment> comments = product.getComments();
        float sum = 0;
        for (int i =0; i < comments.size(); i++){
            sum += comments.get(i).getRating();
        }
        return sum/comments.size();
    }

}