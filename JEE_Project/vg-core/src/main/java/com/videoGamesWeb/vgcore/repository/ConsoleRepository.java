package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Console;
import com.videoGamesWeb.vgcore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsoleRepository extends JpaRepository<Console, Long> {

    @Query("SELECT c FROM Console c WHERE lower(c.name) LIKE %:input%")
    List<Product> searchWithText(String input);
}
