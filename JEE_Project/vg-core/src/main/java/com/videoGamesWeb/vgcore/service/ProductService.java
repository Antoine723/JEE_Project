package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Comment;
import com.videoGamesWeb.vgcore.entity.Product;
import com.videoGamesWeb.vgcore.repository.ConsoleRepository;
import com.videoGamesWeb.vgcore.repository.GameRepository;
import com.videoGamesWeb.vgcore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
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

    public List<Product> findAllById(List<Long> ids){
        return this.productRepository.findAllById(ids);
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

    public float getPriceMin() {
        return Math.min(this.consoleRepository.getPriceMin(), this.gameRepository.getPriceMin());
    }

    public float getPriceMax() {
        return Math.max(this.consoleRepository.getPriceMax(), this.gameRepository.getPriceMax());
    }

    public List<Product> searchWithText(String input, float min_price, float max_price, float min_score) {
        input = input.toLowerCase();
        return Stream.concat(
                        this.consoleRepository.searchWithText(input, min_price, max_price, min_score).stream(),
                        this.gameRepository.searchWithText(input, min_price, max_price, min_score).stream()
                )
                .collect(Collectors.toList());
    }

    public List<Product> searchWithTextAndConsoles(String input, float min_price, float max_price, float min_score, List<String> console_names) {
        input = input.toLowerCase();

        List<Product> consoles = this.consoleRepository.searchWithTextAndNames(input, min_price, max_price, min_score, console_names);

        List<Long> console_ids = this.consoleRepository.searchConsoleIds(console_names);
        List<Product> games = this.gameRepository.searchWithTextAndConsoleIds(input, min_price, max_price, min_score, console_ids);

        return Stream.concat(consoles.stream(), games.stream()).collect(Collectors.toList());
    }
}