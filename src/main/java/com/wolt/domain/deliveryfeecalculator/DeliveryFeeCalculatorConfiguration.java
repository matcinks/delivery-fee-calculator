package com.wolt.domain.deliveryfeecalculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryFeeCalculatorConfiguration {

    @Bean
    DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade() {
        FreeDeliveryCalculator freeDeliveryCalculator = new FreeDeliveryCalculator();
        TotalDeliveryFeeCalculator deliveryFeeCalculator = totalDeliveryFeeCalculator();
        return new DeliveryFeeCalculatorFacade(freeDeliveryCalculator, deliveryFeeCalculator);
    }

    private TotalDeliveryFeeCalculator totalDeliveryFeeCalculator() {
        CartTotalCalculator cartTotalCalculator = new CartTotalCalculator();
        DeliveryDistanceCalculator deliveryDistanceCalculator = new DeliveryDistanceCalculator();
        CartCapacityCalculator cartCapacityCalculator = new CartCapacityCalculator();
        RushHoursCalculator rushHoursCalculator = new RushHoursCalculator();
        FinalFeeValidator finalFeeValidator = new FinalFeeValidator();
        return new TotalDeliveryFeeCalculator(cartTotalCalculator,
                deliveryDistanceCalculator,
                cartCapacityCalculator,
                rushHoursCalculator,
                finalFeeValidator);
    }
}
