package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class Basket {

    private Map<Long, Integer> productsAndQuantities;
    private int userId;


    public Basket(){
        this.productsAndQuantities = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        long key = product.getId();
        int value = quantity;
        if (this.productsAndQuantities.containsKey(key)){ //Si l'article est déjà dans le panier, on augmente la quantité
            this.productsAndQuantities.put(key, this.productsAndQuantities.get(key) + value);
        } else{ //Sinon on l'ajoute juste dedans
            this.productsAndQuantities.put(key, value);
        }
    }

}
