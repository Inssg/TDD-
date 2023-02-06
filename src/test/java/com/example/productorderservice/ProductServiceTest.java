package com.example.productorderservice;

import org.junit.jupiter.api.Test;

public class ProductServiceTest {


    @Test
    상품등록() {
        final String name = "상품명";
        final int price = 1000;
      final  AddProductRequest request = new AddProductRequest("상품명", 1000);
        productService.addProduct(request);
    }

    private class AddProductRequst{
        public AddProductRequst(final String name, final int price) {
            throw new UnsupportedOperationException("Unspported AddProductRequest");
        }
    }



}
