package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.*;
import com.videoGamesWeb.vgcore.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    private final static Logger logger = LoggerFactory.getLogger(OrderService.class);


    public OrderService(final OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public void addOrder(User user, String address, Basket basket){
        Order order = new Order(address, user);
        List<Product> products = this.productService.findAllById(new ArrayList<>(basket.getQtyByProduct().keySet()));
        Product product = products.get(0);
        OrderProduct orderProduct= new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        order.getOrderProducts().add(orderProduct);
        product.getOrderProducts().add(orderProduct);

        /*products.forEach(p -> {
            if (p instanceof Game){
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(order);
                orderProduct.setProduct(p);
                order.getOrderProducts().add(orderProduct);
                p.getOrderProducts().add(orderProduct);
            } else{
                //OrderProduct orderProduct = new OrderProduct(order, p, p.getPrice(), p.getQuantity());
            }
        });
        logger.info("Test {}", order);*/
        this.orderRepository.save(order);
    }

    public List<Order> findAllByUserId(long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }
}