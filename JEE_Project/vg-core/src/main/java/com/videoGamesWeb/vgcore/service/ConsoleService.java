package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.repository.ConsoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsoleService {

    private final ConsoleRepository consoleRepository;

    public ConsoleService(final ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

}