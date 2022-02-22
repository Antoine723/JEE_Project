package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Game;
import com.videoGamesWeb.vgcore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT min(g.price) FROM Game g")
    float getPriceMin();

    @Query("SELECT max(g.price) FROM Game g")
    float getPriceMax();

    @Query("SELECT g FROM Game g WHERE lower(g.name) LIKE %:input%")
    List<Product> searchWithText(String input);

    @Query("SELECT DISTINCT g FROM Game g JOIN g.consoles c WHERE lower(g.name) LIKE %:input% AND c.id IN :console_ids")
    List<Product> searchWithTextAndConsoleIds(String input, List<Long> console_ids);
}
