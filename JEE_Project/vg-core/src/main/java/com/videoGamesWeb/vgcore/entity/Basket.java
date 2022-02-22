package com.videoGamesWeb.vgcore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.videoGamesWeb.vgcore.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class Basket {

    private Map<Long, Integer> productsIdAndQuantities;
    private int userId;

    @JsonIgnore
    private Map<Product, Integer> productsAndQuantities;

    private final static Logger logger = LoggerFactory.getLogger(Basket.class);

    public Basket(){
        this.productsIdAndQuantities = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        long key = product.getId();
        int value = quantity;
        if (this.productsIdAndQuantities.containsKey(key)){ //Si l'article est déjà dans le panier, on augmente la quantité
            this.productsIdAndQuantities.put(key, this.productsIdAndQuantities.get(key) + value);
        } else{ //Sinon on l'ajoute juste dedans
            this.productsIdAndQuantities.put(key, value);
        }
    }

    public float computeTotalAmount(){
        float totalAmount = 0;
        if (this.productsAndQuantities != null) {
            for(Map.Entry<Product, Integer> entry : this.productsAndQuantities.entrySet()){
                totalAmount += entry.getKey().getPrice() * entry.getValue();
            }
            return totalAmount;
        } else {
            return -1;
        }
    }

}
