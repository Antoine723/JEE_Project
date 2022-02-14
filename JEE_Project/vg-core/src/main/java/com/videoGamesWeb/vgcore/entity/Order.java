package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Order")
@Getter @Setter
public class Order extends GenericEntity {

    @Column(name = "orderNumber")
    private UUID orderNumber;

    @Column(name = "address")
    private String address;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Product> products = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
