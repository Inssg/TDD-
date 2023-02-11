package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.order.OrderRepository;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import org.springframework.stereotype.Component;

@Component
class PaymentAdapter implements PaymentPort {
    private final PaymentGateWay paymentGateWay;
    private final PaymentRepository paymentRepository;
    private final OrderRepository  orderRepository;

    PaymentAdapter(PaymentGateWay paymentGateWay, PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentGateWay = paymentGateWay;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
    }

    @Override
    public void pay(final int totalPrice, final String cardNumber) {
        paymentGateWay.execute(totalPrice, cardNumber);

    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
