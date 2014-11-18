package com.orders.rest.controllers;

import com.orders.domain.Order;
import com.orders.repositories.OrderMemoryRepository;
import com.orders.repositories.OrderRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by marco on 07/11/14.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest {

    private static Logger LOG = LoggerFactory.getLogger(OrderControllerTest.class);

    MockMvc mockMvc;

    @Mock
    OrderMemoryRepository orderRepository;

    @InjectMocks
    OrderController controller;

    UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");
    UUID prodKey = UUID.randomUUID();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatGetAllOrdersReturnsHttpOk() throws Exception {

        LOG.info("START thatGetAllOrdersReturnsHttpOk");
        Map<UUID, Order> orders = new HashMap<UUID, Order>();
        orders.put(key, new Order(key, 1, prodKey ));

        when(orderRepository.findAll()).thenReturn(new ArrayList(orders.values()));


        this.mockMvc.perform(
                get("/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void thatGetOneOrderReturnsHttpOK() throws Exception {

        LOG.info("START thatGetOneOrderReturnsHttpOK");

        Map<UUID, Order> orders = new HashMap<UUID, Order>();
        orders.put(key, new Order(key, 1, prodKey ));

        when(orderRepository.findById(key)).thenReturn(orders.get(key));

        this.mockMvc.perform(
                get("/orders/{id}", key.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()
                );

    }

    @Test
    public void thatViewOrderRendersCorrectly() throws Exception {

        LOG.info("START thatViewOrderRendersCorrectly");

        Map<UUID, Order> orders = new HashMap<UUID, Order>();
        orders.put(key, new Order(key, 1, prodKey ));

        when(orderRepository.findById(key)).thenReturn(orders.get(key));


        this.mockMvc.perform(
                get("/orders/{id}", key.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.identifier").value(key.toString()));
    }


}
