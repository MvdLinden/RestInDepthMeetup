package com.orders.rest.controllers;

import com.orders.domain.Order;
import com.orders.repositories.OrderMemoryRepository;
import com.orders.rest.resources.OrderResource;
import com.orders.rest.resources.OrderResourceAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by marco on 03/11/14.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderMemoryRepository orderRepository;

    private OrderResourceAssembler assembler = new OrderResourceAssembler();

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderResource> getAllOrders() {
        List<OrderResource> orderResources = new ArrayList<OrderResource>();

        orderResources = assembler.toResources(orderRepository.findAll());

        return orderResources;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<OrderResource> viewOrder(@PathVariable String id) {

        Order order = orderRepository.findById(UUID.fromString(id));

        if (order == null) {
            return new ResponseEntity<OrderResource>(HttpStatus.NOT_FOUND);
        }
        OrderResource resource = assembler.toResource(order);

        return new ResponseEntity<OrderResource>(resource, HttpStatus.OK);
    }


        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<OrderResource> createOrder(@RequestBody OrderResource orderResource, UriComponentsBuilder builder) {

            Order responseOrder = orderRepository.save(assembler.toClass(orderResource));
            OrderResource responseResource = assembler.toResource(responseOrder);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(
                    builder.path("/orders/{id}")
                            .buildAndExpand(responseOrder.getIdentifier().toString()).toUri());

            return new ResponseEntity<OrderResource>(responseResource, headers, HttpStatus.CREATED);
        }

        @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
        public ResponseEntity<OrderResource> cancelOrder(@PathVariable String id) {

            boolean deleted = orderRepository.delete(UUID.fromString(id));

            if (!deleted) {
                return new ResponseEntity<OrderResource>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<OrderResource>(HttpStatus.OK);
            }

        }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<OrderResource> updateOrder(@PathVariable String id) {

        return new ResponseEntity<OrderResource>(HttpStatus.NOT_IMPLEMENTED);

    }

}
