package com.orders.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by marco on 03/11/14.
 */

public class OrderResource extends ResourceSupport {

    // TODO: check if identifier can be called id. Conflicts with ResourceSupport which has a getId() that returns a link.
    // TODO: this looks like Order. can we avoid duplication somehow?
    // Note: Need setters because of BeanUtils.copy method

    private UUID identifier;
    private Integer amount;
    private UUID productId;

    @JsonCreator
    public OrderResource(@JsonProperty(value = "identifier") @NotNull UUID identifier, @JsonProperty(value = "amount") @NotNull Integer amount, @JsonProperty(value = "productId") @NotNull UUID productId) {

        this.identifier = identifier;
        this.amount = amount;
        this.productId = productId;
    }

    public OrderResource() {
    }

    public Integer getAmount() {
        return amount;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getIdentifier() { return identifier; }
    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

}
