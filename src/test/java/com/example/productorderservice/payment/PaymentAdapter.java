package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import org.springframework.stereotype.Component;

@Component
class PaymentAdapter implements PaymentPort {
    private final PaymentGateWay paymentGateWay;
    private final PaymentRepository paymentRepository;

    PaymentAdapter(PaymentGateWay paymentGateWay, PaymentRepository paymentRepository) {
        this.paymentGateWay = paymentGateWay;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Order getOrder(Long orderId) {
        return new Order(new Product("상품1", 1000, DiscountPolicy.NONE), 2);
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
