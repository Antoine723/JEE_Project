package com.videoGamesWeb.vgcore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    public Order(){
    }

    public Order(String address, User user){
        this.orderNumber = UUID.randomUUID();
        this.address = address;
        this.user = user;
        this.orderProducts = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long id;

    @Column(name = "orderNumber")
    private UUID orderNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> orderProducts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
