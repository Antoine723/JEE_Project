package com.videoGamesWeb.vgcore.service;


import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductService productService;

    public CommentService(final CommentRepository commentRepository,
                          final ProductService productService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
    }

    public void addComment(Comment comment){
        this.commentRepository.save(comment);
        this.productService.updateRatingAverage(comment.getProduct());
    }

}