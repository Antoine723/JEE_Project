package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class Basket {

    private Map<Long, Map<Long, Integer>> qtyByProduct = new HashMap<>();

    public void updateProductQty(Long productId, Long consoleId, int quantity, int max) {
        if (!this.qtyByProduct.containsKey(productId)) this.qtyByProduct.put(productId, new HashMap<>());
        quantity = Math.min(max, Math.max(1, (this.qtyByProduct.get(productId).getOrDefault(consoleId, 0)) + quantity));
        this.qtyByProduct.get(productId).put(consoleId, quantity);
    }

    public void removeProduct(long productId, long consoleId) {
        this.qtyByProduct.get(productId).remove(consoleId);
        if (this.qtyByProduct.get(productId).isEmpty()) this.qtyByProduct.remove(productId);
    }
}
