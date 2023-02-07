package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ProductApiTest extends ApiTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품등록() {
        final var request = ProductSteps.상품등록요청_생성();

        //요청을 보내는 로그를 남긴다
        final var response = ProductSteps.상품등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    void 상품조회() {
        //상품등록
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        Long productId = 1L;

        //상품조회
        final var response = ProductSteps.상품조회요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }


}
