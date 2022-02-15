package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
