package com.videoGamesWeb.vgcore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter
@DiscriminatorColumn(name="product_type",
discriminatorType = DiscriminatorType.INTEGER)
public abstract class Product {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "release_date")
    private Date releaseDate;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @ManyToMany //TODO à lier avec panier plutôt ?
    @JoinTable(name="orders_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name="order_id"))
    List<Order> orders;
}
