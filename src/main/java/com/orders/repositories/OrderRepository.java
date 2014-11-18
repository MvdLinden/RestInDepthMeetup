package com.orders.repositories;

import com.orders.domain.Order;

/**
 * Created by marco on 03/11/14.
 */
import java.util.List;
import java.util.UUID;


public interface OrderRepository {

    Order save(Order order);

    boolean delete(UUID key);

    Order findById(UUID key);

    List<Order> findAll();
}

