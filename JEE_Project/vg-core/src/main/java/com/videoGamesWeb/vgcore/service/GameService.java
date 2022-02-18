package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Game;
import com.videoGamesWeb.vgcore.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll(){
        return this.gameRepository.findAll();
    }

}