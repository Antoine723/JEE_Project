package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.Basket;
import com.videoGamesWeb.vgcore.entity.Order;
import com.videoGamesWeb.vgcore.entity.User;
import com.videoGamesWeb.vgcore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(final OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public void addOrder(User user, String address, Basket basket){
        Order order = new Order();
        order.setAddress(address);
        order.setProducts(this.productService.findAllById(new ArrayList<>(basket.getQtyByProduct().keySet())));
        order.setUser(user);
        this.orderRepository.save(order);
    }

    public List<Order> findAllByUserId(long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    public void deleteAllByUserId(long userId) {
        this.orderRepository.deleteAllByUserId(userId);
    }
}