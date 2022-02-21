package com.videoGamesWeb.vgcore.service;


import com.videoGamesWeb.vgcore.dto.CommentDTO;
import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.repository.CommentRepository;
import com.videoGamesWeb.vgcore.repository.ProductRepository;
import com.videoGamesWeb.vgcore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public CommentService(final CommentRepository commentRepository,
                          final ProductRepository productRepository,
                          final UserRepository userRepository,
                          final ProductService productService) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public void addComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setContent(commentDTO.getComment());
        comment.setRating(commentDTO.getRating());
        Optional<Product> productOpt = this.productRepository.findById(commentDTO.getProductId());
        if (productOpt.isPresent()){
            Product product = productOpt.get();
            comment.setProduct(productOpt.get());
            this.commentRepository.save(comment);
            this.productService.updateRatingAverage(product);
        }
        Optional<User> userOpt = this.userRepository.findById(commentDTO.getUserId());
        if (userOpt.isPresent()){
            comment.setUser(userOpt.get());
            this.commentRepository.save(comment);
        }
    }

}