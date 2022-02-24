package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Order;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;

    public OrderService(final OrderRepository orderRepository, BasketService basketService) {
        this.orderRepository = orderRepository;
        this.basketService = basketService;
    }

    public void addOrder(User user, String address, Basket basket){
        Order order = new Order();
        order.setAddress(address);
        order.setProducts(basketService.getProductsList(basket));
        order.setUser(user);
        this.orderRepository.save(order);
    }

}