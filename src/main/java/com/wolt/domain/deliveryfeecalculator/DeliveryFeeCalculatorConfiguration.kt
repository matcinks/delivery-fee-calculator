package com.wolt.domain.deliveryfeecalculator

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DeliveryFeeCalculatorConfiguration {
    @Bean
    open fun deliveryFeeCalculatorFacade(): DeliveryFeeCalculatorFacade {
        val freeDeliveryCalculator = FreeDeliveryCalculator()
        val deliveryFeeCalculator = totalDeliveryFeeCalculator()
        return DeliveryFeeCalculatorFacade(freeDeliveryCalculator, deliveryFeeCalculator)
    }

    private fun totalDeliveryFeeCalculator(): TotalDeliveryFeeCalculator {
        val cartTotalCalculator = CartTotalCalculator()
        val deliveryDistanceCalculator = DeliveryDistanceCalculator()
        val cartCapacityCalculator = CartCapacityCalculator()
        val rushHoursCalculator = RushHoursCalculator()
        val finalFeeValidator = FinalFeeValidator()
        return TotalDeliveryFeeCalculator(cartTotalCalculator,
                deliveryDistanceCalculator,
                cartCapacityCalculator,
                rushHoursCalculator,
                finalFeeValidator)
    }
}