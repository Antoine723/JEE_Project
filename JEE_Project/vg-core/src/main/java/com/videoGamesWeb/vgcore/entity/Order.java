package com.videoGamesWeb.vgcore.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter @Setter
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Order {

    public Order(){
        this.orderNumber = UUID.randomUUID();
        this.products = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long id;

    @Column(name = "orderNumber")
    private UUID orderNumber;

    @Column(name = "address")
    private String address;

    @ManyToMany
    @JoinTable(name="orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Type(type="json")
    @Column(columnDefinition = "json")
    private Map<Long, Map<Long, Integer>> productsQuantity = new HashMap<>();

}
