package com.sanedge.product_service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sanedge.product_service.service.ProductService;

@Component
public class OrderReceiver {

    private final ProductService productService;

    @Autowired
    public OrderReceiver(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "${order.rabbitmq.queue}")
    public void receiveOrderMessage(String message) {
        String[] parts = message.split(":");
        if (parts.length == 2) {
            Long productId = Long.parseLong(parts[0]);
            int quantity = Integer.parseInt(parts[1]);
            productService.reduceProductQuantity(productId, quantity);
        }
    }
}
