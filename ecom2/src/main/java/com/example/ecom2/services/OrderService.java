package com.example.ecom2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom2.daos.ItemDAO;
import com.example.ecom2.daos.OrderDAO;
import com.example.ecom2.models.Order;

@Service
public class OrderService {
    private final OrderDAO orderRepository;

    @Autowired
    public OrderService(OrderDAO orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
