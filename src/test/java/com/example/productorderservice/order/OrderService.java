package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;

class OrderService {
    private final OrderPort orderPort;

    OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public void createOrder(final CreateOrderRequest request) {
        Product product = orderPort.getProductById(request.productId());

        Order order = new Order(product, request.quantity());

        orderPort.save(order);

    }

}
