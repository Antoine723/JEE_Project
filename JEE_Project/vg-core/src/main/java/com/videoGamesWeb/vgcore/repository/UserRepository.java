package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u WHERE u.name=:name")
    int countByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.name=:name")
    Optional<User> findByName(@Param("name") String name);
}
