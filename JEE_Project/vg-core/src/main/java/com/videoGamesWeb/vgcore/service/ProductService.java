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

    public void updateRatingAverage(Product product){
        List<Comment> comments = product.getComments();
        if (comments.size() == 0) return;

        product.setRating((float) comments.stream().mapToInt(Comment::getRating).sum() / comments.size());
        this.productRepository.save(product);
    }

}