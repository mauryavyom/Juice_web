package com.example.demo.services;

import com.example.demo.dto.CheckoutItem;
import com.example.demo.Model.Order;
import com.example.demo.Repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServices {
    @Autowired
    private OrderRepository orderRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Order saveOrder(String userId, double totalAmount, List<CheckoutItem> items) throws JsonProcessingException {
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());

        String itemsJson = objectMapper.writeValueAsString(items);
        order.setItemsJson(itemsJson);

        return orderRepository.save(order);
    }
}