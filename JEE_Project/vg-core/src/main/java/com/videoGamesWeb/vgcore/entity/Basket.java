package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class Basket {

    private Map<Long, Integer> qtyByProduct = new HashMap<>();

    public void addProductQty(Long productId, int quantity){
        this.qtyByProduct.put(productId, (this.qtyByProduct.getOrDefault(productId, 0)) + quantity);
    }

    public void reduceProductQty(long productId, int quantity) {
        this.qtyByProduct.put(productId, Math.max(1, (this.qtyByProduct.getOrDefault(productId, 0)) - quantity));
    }

    public void removeProduct(long productId) {
        this.qtyByProduct.remove(productId);
    }
}
