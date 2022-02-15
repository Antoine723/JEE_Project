package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
