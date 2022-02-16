package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter
@DiscriminatorColumn(name="product_type",
discriminatorType = DiscriminatorType.INTEGER)
public abstract class Product extends GenericEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "rating")
    private float rating;

    @Column(name = "img")
    private String img;

    @Column(name = "releaseYear")
    private Date releaseYear;

}
