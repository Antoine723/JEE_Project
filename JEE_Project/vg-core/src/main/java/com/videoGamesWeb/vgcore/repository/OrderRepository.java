package com.videoGamesWeb.vgcore.repository;


import com.videoGamesWeb.vgcore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.id=:userId")
    List<Order> findAllByUserId(long userId);

    @Query("DELETE FROM Order o WHERE o.user.id=:userId")
    void deleteAllByUserId(long userId);
}
