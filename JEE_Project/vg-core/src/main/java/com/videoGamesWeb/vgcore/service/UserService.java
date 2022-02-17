package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existById(long userId) {
        return this.userRepository.existsById(userId);
    }

    public void deleteById(long userId) {
        this.userRepository.deleteById(userId);
    }
}