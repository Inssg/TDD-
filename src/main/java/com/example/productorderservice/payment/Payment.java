package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import org.springframework.util.Assert;

class Payment {
    private final Order order;
    private final String cardNumber;
    private Long id;

    public Payment(final Order order, final String cardNumber) {
        this.order = order;
        this.cardNumber = cardNumber;
        Assert.notNull(order, "주문 ID는 필수입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수 입니다.");
    }

    public void assignId(int i) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return order.getTotalPrice();
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
