package com.orders.domain;

import java.util.UUID;

/**
 * Created by marco on 03/11/14.
 */
public class Order {

    private UUID identifier;
    private Integer amount;
    private UUID productId;

    public Order(UUID identifier, Integer amount, UUID productId) {
        this.identifier = identifier;
        this.amount = amount;
        this.productId = productId;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public Integer getAmount() {
        return amount;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String toString() {
        return "UUID: " + identifier + " amount: " + amount + " productID: " + productId;
    }
}
