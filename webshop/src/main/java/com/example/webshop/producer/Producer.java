package com.example.webshop.producer;

import javax.jms.Queue;

import com.example.webshop.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.webshop.model.Product;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/produce")
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @PostMapping("/message")
    public ArrayList<Product> sendMessage(@RequestBody ArrayList<Product> cart) {

        try {
            logger.info("Message received");
            ObjectMapper mapper = new ObjectMapper();
            String productAsJson = mapper.writeValueAsString(cart);

            jmsTemplate.convertAndSend(queue, productAsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }
}
