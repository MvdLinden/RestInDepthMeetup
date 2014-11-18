package com.orders.rest.controllers;


import com.orders.Application;
import com.orders.rest.resources.OrderResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertTrue;



/**
 * Created by marco on 07/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class})
public class OrderControllerHttpTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void thatOrdersCanBeQueried() throws Exception{

        this.mockMvc.perform(
                get("/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        //ResponseEntity<Order> entity = createOrder();
        //HttpEntity<String> requestEntity = new HttpEntity<String>();

       // RestTemplate template = new RestTemplate();
       // String result = template.getForObject(
        //        "http://localhost:8080/orders",
        //        String.class);
      // System.out.println(result);
       // String path = entity.getHeaders().getLocation().getPath();

        //assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        //assertTrue(path.startsWith("/aggregators/orders/"));
        //Order order = entity.getBody();

        //System.out.println ("The Order ID is " + order.getKey());
        //System.out.println ("The Location is " + entity.getHeaders().getLocation());

        //assertEquals(2, order.getItems().size());
    }
   /*
    @Test
    public void thatOrdersCannotBeAddedAndQueriedWithBadUser() {

        HttpEntity<String> requestEntity = new HttpEntity<String>(
                RestDataFixture.standardOrderJSON(),
                getHeaders("letsnosh" + ":" + "BADPASSWORD"));

        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<Order> entity = template.postForEntity(
                    "http://localhost:8080/aggregators/orders",
                    requestEntity, Order.class);

            fail("Request Passed incorrectly with status " + entity.getStatusCode());
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
        }
    }

    // {!begin thatOrdersHaveCorrectHateoasLinks}
    @Test
    public void thatOrdersHaveCorrectHateoasLinks() throws Exception{

        this.mockMvc.perform(
                get("/orders/{id}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        String orderBase = "/aggregators/orders/" + order.getKey();

        assertEquals(entity.getHeaders().getLocation().toString(), order.getLink("self").getHref());
        assertTrue(order.getLink("Order Status").getHref().endsWith(orderBase + "/status"));
    }
    // {!end thatOrdersHaveCorrectHateoasLinks}



    private ResponseEntity<OrderResource> createOrder() {
        HttpEntity<String> requestEntity = new HttpEntity<String>(
                RestDataFixture.standardOrderJSON(),getHeaders("letsnosh" + ":" + "noshing"));

        RestTemplate template = new RestTemplate();
        return template.postForEntity(
                "http://localhost:8080/aggregators/orders",
                requestEntity, Order.class);
    }
    */
    static HttpHeaders getHeaders(String auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
        //headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

        return headers;
    }

}
