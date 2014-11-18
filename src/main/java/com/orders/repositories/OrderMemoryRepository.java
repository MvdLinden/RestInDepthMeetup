package com.orders.repositories;

import com.orders.domain.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by marco on 03/11/14.
 */
@Component
public class OrderMemoryRepository implements OrderRepository {


    private Map<UUID, Order> orders = Collections.unmodifiableMap(new HashMap<UUID, Order>());

    //public OrderMemoryRepository(final Map<UUID, Order> orders) {
    //    this.orders = Collections.unmodifiableMap(orders);
    //}

    public OrderMemoryRepository(){
        Order order = new Order(UUID.randomUUID(), 1, UUID.randomUUID());
        Map<UUID, Order> modifiableOrders = new HashMap<UUID, Order>(orders);
        modifiableOrders.put(order.getIdentifier(), order);
        this.orders = Collections.unmodifiableMap(modifiableOrders);
    }

    @Override
    public synchronized Order save(Order order) {

        Map<UUID, Order> modifiableOrders = new HashMap<UUID, Order>(orders);
        modifiableOrders.put(order.getIdentifier(), order);
        this.orders = Collections.unmodifiableMap(modifiableOrders);

        return order;
    }

    @Override
    public synchronized boolean delete(UUID key) {
        boolean deleted = false;
        if (orders.containsKey(key)) {
            Map<UUID, Order> modifiableOrders = new HashMap<UUID, Order>(orders);
            modifiableOrders.remove(key);
            this.orders = Collections.unmodifiableMap(modifiableOrders);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public Order findById(UUID key) {
        return orders.get(key);
    }

    @Override
    public List<Order> findAll() {
        return Collections.unmodifiableList(new ArrayList<Order>(orders.values()));
    }

}
