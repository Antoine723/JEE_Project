package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.repository.ConsoleRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {

    private final ConsoleRepository consoleRepository;

    public ConsoleService(final ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

}