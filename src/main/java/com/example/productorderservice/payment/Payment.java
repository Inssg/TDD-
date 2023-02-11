package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private  Order order;

    private  String cardNumber;

    public Payment(final Order order, final String cardNumber) {
        this.order = order;
        this.cardNumber = cardNumber;
        Assert.notNull(order, "주문 ID는 필수입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수 입니다.");
    }

    public int getPrice() {
        return order.getTotalPrice();
    }

}
