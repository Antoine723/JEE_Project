package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
