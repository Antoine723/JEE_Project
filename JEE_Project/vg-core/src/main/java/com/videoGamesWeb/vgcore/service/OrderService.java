package com.videoGamesWeb.vgcore.service;

import com.videoGamesWeb.vgcore.entity.*;
import com.videoGamesWeb.vgcore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(final OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Map<Order, Float> computeTotalAmountOfOrders(List<Order> orders){
        Map<Order, Float> ordersAndPrice = new HashMap<>();
        orders.forEach(o->{
            Map<Long, Map<Long, Integer>> productsQuantity = o.getProductsQuantity();
            float price = 0;
            for (Map.Entry<Long, Map<Long, Integer>> pq : productsQuantity.entrySet()){
                Optional<Product> productOpt = this.productService.findById(pq.getKey());
                if (productOpt.isPresent()){
                    Product product = productOpt.get();
                    if (product instanceof Console){
                        price += product.getPrice() * pq.getValue().get(pq.getKey());
                    } else {
                        for (Map.Entry<Long, Integer> cq : pq.getValue().entrySet()){
                            long consoleId = cq.getKey();
                            List<GameConsole> gc = ((Game)product).getGameConsoles();
                            for (int i=0; i<gc.size(); i++){
                                if (consoleId == gc.get(i).getConsole().getId()){
                                    price += gc.get(i).getPrice() * cq.getValue();
                                }
                            }

                        }
                    }
                    //price += product.getPrice() * pq.getValue();
                }
            }
            ordersAndPrice.put(o, price);

        });
        return ordersAndPrice;
    }
    public void addOrder(User user, String address, Basket basket){
        Order order = new Order();
        order.setAddress(address);
        List<Product> products = this.productService.findAllById(new ArrayList<>(basket.getQtyByProduct().keySet()));
        order.setProducts(products);
        products.forEach(p->{
            order.getProductsQuantity().put(p.getId(), basket.getQtyByProduct().get(p.getId()));
        });
        order.setUser(user);
        this.orderRepository.save(order);
    }

    public List<Order> findAllByUserId(long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }
}