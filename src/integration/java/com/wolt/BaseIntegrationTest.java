package com.wolt;

import com.wolt.domain.DeliveryFeeCalculatorApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DeliveryFeeCalculatorApp.class})
public class BaseIntegrationTest {

    @Test
    void f() {
        System.out.println("test");
    }

}
