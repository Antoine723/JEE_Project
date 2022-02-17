package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(u) FROM User u WHERE u.name=:name")
    int countByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.name=:name")
    User getByName(@Param("name") String name);
}
