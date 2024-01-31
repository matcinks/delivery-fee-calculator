package com.wolt.domain.deliveryfeecalculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryFeeCalculatorConfiguration {

    @Bean
    DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade(){
        CartCapacityCalculator cartCapacityCalculator = new CartCapacityCalculator();
        CartTotalCalculator cartTotalCalculator = new CartTotalCalculator();
        DeliveryDistanceCalculator deliveryDistanceCalculator = new DeliveryDistanceCalculator();
        RushHoursValidator rushHoursValidator = new RushHoursValidator();
        FinalFeeValidator finalFeeValidator = new FinalFeeValidator();
        return new DeliveryFeeCalculatorFacade(cartCapacityCalculator, cartTotalCalculator, deliveryDistanceCalculator, rushHoursValidator, finalFeeValidator);
    }
}
