package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Game;
import com.videoGamesWeb.vgcore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT min(c.price) FROM Game g JOIN g.gameConsoles c")
    float getPriceMin();

    @Query("SELECT max(c.price) FROM Game g JOIN g.gameConsoles c")
    float getPriceMax();

    @Query("SELECT DISTINCT g FROM Game g JOIN g.gameConsoles c " +
            "WHERE lower(g.name) LIKE %:input% " +
            "AND c.price >= :min_price AND c.price <= :max_price " +
            "AND (g.rating IS NULL OR g.rating >= :min_score)")
    List<Product> searchWithText(String input, float min_price, float max_price, float min_score);

    @Query("SELECT DISTINCT g FROM Game g JOIN g.gameConsoles c " +
            "WHERE lower(g.name) LIKE %:input% " +
            "AND c.price >= :min_price AND c.price <= :max_price " +
            "AND (g.rating IS NULL OR g.rating >= :min_score)" +
            "AND c.console.id IN :console_ids ")
    List<Product> searchWithTextAndConsoleIds(String input, float min_price, float max_price, float min_score, List<Long> console_ids);
}
