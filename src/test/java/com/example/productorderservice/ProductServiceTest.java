package com.example.productorderservice;

import org.junit.jupiter.api.Test;

public class ProductServiceTest {


    @Test
    상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
      final  AddProductRequest request = new AddProductRequest("상품명", 1000, discountPolicy);
        productService.addProduct(request);
    }

    private class AddProductRequest{
        public AddProductRequest(final String name, final int price) {
            throw new UnsupportedOperationException("Unspported AddProductRequest");
        }
    }
    private enum DiscountPolicy{

        NONE
    }





}
