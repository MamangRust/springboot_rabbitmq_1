package com.sanedge.order_service.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {

    private final AmqpTemplate rabbitTemplate;

    @Value("${order.rabbitmq.exchange}")
    private String exchange;

    @Value("${order.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    public OrderSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderMessage(Long productId, int quantity) {
        String message = productId + ":" + quantity;
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println("Order message sent to RabbitMQ: " + message);
    }
}
