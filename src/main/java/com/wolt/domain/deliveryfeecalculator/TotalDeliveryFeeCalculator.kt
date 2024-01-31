package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger

internal class TotalDeliveryFeeCalculator(
    private val cartTotalCalculator: CartTotalCalculator,
    private val deliveryDistanceCalculator: DeliveryDistanceCalculator,
    private val cartCapacityCalculator: CartCapacityCalculator,
    private val rushHoursCalculator: RushHoursCalculator,
    private val finalFeeValidator: FinalFeeValidator
) {
    fun calculateTotal(orderData: OrderData): BigInteger {
        return finalFeeValidator
            .validate(
                rushHoursCalculator
                    .calculate(
                        orderData.orderTime,
                        calculateBaseDelivery(
                            orderData.cartValue,
                            orderData.deliveryDistance,
                            orderData.numberOfItems
                        )
                    )
            )
    }

    private fun calculateBaseDelivery(
        cartValue: BigInteger,
        deliveryDistance: BigInteger,
        numberOfItems: BigInteger
    ): BigInteger {
        return cartTotalCalculator.calculate(cartValue)
            .add(deliveryDistanceCalculator.calculate(deliveryDistance))
            .add(cartCapacityCalculator.calculate(numberOfItems))
    }
}