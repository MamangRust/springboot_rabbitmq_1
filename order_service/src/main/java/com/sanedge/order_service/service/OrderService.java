package com.sanedge.order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.order_service.model.Order;
import com.sanedge.order_service.rabbitmq.OrderSender;
import com.sanedge.order_service.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderSender orderSender;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderSender orderSender) {
        this.orderRepository = orderRepository;
        this.orderSender = orderSender;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
        orderSender.sendOrderMessage(order.getProductId(), order.getQuantity());
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
