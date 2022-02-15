package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
