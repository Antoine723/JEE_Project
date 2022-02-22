package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.repository.ConsoleRepository;
import com.videoGamesWeb.vgcore.repository.GameRepository;
import com.videoGamesWeb.vgcore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ConsoleRepository consoleRepository;
    private final GameRepository gameRepository;

    public ProductService(ProductRepository productRepository,
                          ConsoleRepository consoleRepository,
                          GameRepository gameRepository) {
        this.productRepository = productRepository;
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
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

    public List<Product> searchWithTextAndFilters(String input, List<String> consoles) {
        input = input.toLowerCase();
        return Stream.concat(
                    this.consoleRepository.searchWithText(input).stream(),
                    this.gameRepository.searchWithText(input).stream()
                )
                .collect(Collectors.toList());
    }

    public float getPriceMin() {
        return Math.min(this.consoleRepository.getPriceMin(), this.gameRepository.getPriceMin());
    }

    public float getPriceMax() {
        return Math.max(this.consoleRepository.getPriceMin(), this.gameRepository.getPriceMin());
    }
}