package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.User;
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

    public boolean existByName(String name) {
        return userRepository.countByName(name) > 0;
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public User getByName(String name) {
        return this.userRepository.getByName(name);
    }

    public User getById(long userId) {
        return this.userRepository.getById(userId);
    }

    public void deleteById(long userId) {
        this.userRepository.deleteById(userId);
    }
}