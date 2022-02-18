package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Console;
import com.videoGamesWeb.vgcore.repository.ConsoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsoleService {

    private final ConsoleRepository consoleRepository;

    public ConsoleService(final ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    public List<Console> findAll(){
        return this.consoleRepository.findAll();
    }
}