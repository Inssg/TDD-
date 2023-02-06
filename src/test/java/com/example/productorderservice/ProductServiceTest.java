package com.example.productorderservice;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ProductServiceTest {


    @Test
    상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
      final  AddProductRequest request = new AddProductRequest("상품명", 1000, discountPolicy);
        productService.addProduct(request);
    }

    private static class AddProductRequest{
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public AddProductRequest(final String name, final int price,DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.isTrue(price > 0, "상품가격은 0보다 커야 합니다.");
            Assert.notNull(discountPolicy,"할인 정책은 필수입니다.");
            this.name = name;
            this.price =price;
            this.discountPolicy = discountPolicy;
        }
    }
    private enum DiscountPolicy{

        NONE
    }





}
