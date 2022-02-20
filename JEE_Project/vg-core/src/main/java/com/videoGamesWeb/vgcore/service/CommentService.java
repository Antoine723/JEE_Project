package com.videoGamesWeb.vgcore.service;


import com.videoGamesWeb.vgcore.dto.CommentDTO;
import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.repository.CommentRepository;
import com.videoGamesWeb.vgcore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public CommentService(final CommentRepository commentRepository, ProductRepository productRepository) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
    }

    public void addComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setContent(commentDTO.getComment());
        comment.setRating(commentDTO.getRating());
        Optional<Product> productOpt = this.productRepository.findById(commentDTO.getProductId());
        if (productOpt.isPresent()){
            comment.setProduct(productOpt.get());
            this.commentRepository.save(comment);
        }
    }

}