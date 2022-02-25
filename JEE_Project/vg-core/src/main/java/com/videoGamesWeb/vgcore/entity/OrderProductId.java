package com.videoGamesWeb.vgcore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class OrderProductId implements Serializable {

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;
}
