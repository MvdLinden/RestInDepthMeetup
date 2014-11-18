package com.orders.rest.resources;

import com.orders.domain.Order;
import com.orders.rest.controllers.OrderController;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by marco on 06/11/14.
 */
public class OrderResourceAssembler extends ResourceAssemblerSupport<Order, OrderResource> {

    public OrderResourceAssembler() {
        super(OrderController.class, OrderResource.class);
    }

    @Override
    public OrderResource toResource(Order order) {

        OrderResource resource = createResourceWithId(order.getIdentifier(), order);
        BeanUtils.copyProperties(order, resource);

        return resource;
    }

    public Order toClass(OrderResource resource){

        return new Order(resource.getIdentifier(), resource.getAmount(), resource.getProductId());

    }
}
