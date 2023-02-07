package com.example.productorderservice;


import com.example.productorderservice.product.DatabaseCleanup;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {
        @Autowired
        private DatabaseCleanup databaseCleanup;

        @LocalServerPort
        private int port;

    @BeforeEach
    void setUp() throws Exception {
        //어플리케이션 띄우면 캐싱이되서 다른 테스트에서 상품 여러번 등록 수정시 테스트 꼬일수있다.
        //테스트 격리를 위해 데이터베이스를 테스트케이스마다 Before에  데이터베이스를 초기화 해주자 -> Database Cleanup
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
            databaseCleanup.afterPropertiesSet();
        }
            databaseCleanup.execute();
    }

}
