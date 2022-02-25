package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private float boughtPrice;

    private int selectedQuantity;

    private String console;

    public OrderProduct(){
    }

    public OrderProduct(Order order, Product product, float boughtPrice, int selectedQuantity){
        this.order = order;
        this.product = product;
        this.boughtPrice = boughtPrice;
        this.selectedQuantity = selectedQuantity;
    }

    public OrderProduct(Order order, Product product, float boughtPrice, int selectedQuantity, String console){
        this.order = order;
        this.product = product;
        this.boughtPrice = boughtPrice;
        this.selectedQuantity = selectedQuantity;
        this.console = console;
    }


}
