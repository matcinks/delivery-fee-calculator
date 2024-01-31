package com.wolt.domain.deliveryfeecalculator

import lombok.AllArgsConstructor
import java.math.BigInteger

@AllArgsConstructor
internal class TotalDeliveryFeeCalculator {
    private val cartTotalCalculator: CartTotalCalculator? = null
    private val deliveryDistanceCalculator: DeliveryDistanceCalculator? = null
    private val cartCapacityCalculator: CartCapacityCalculator? = null
    private val rushHoursCalculator: RushHoursCalculator? = null
    private val finalFeeValidator: FinalFeeValidator? = null
    fun calculateTotal(orderData: OrderData?): BigInteger? {
        return finalFeeValidator
                .validate(rushHoursCalculator
                        .calculate(orderData!!.orderTime,
                                calculateBaseDelivery(orderData.cartValue,
                                        orderData.deliveryDistance,
                                        orderData.numberOfItems)))
    }

    private fun calculateBaseDelivery(cartValue: BigInteger, deliveryDistance: BigInteger, numberOfItems: BigInteger): BigInteger {
        return cartTotalCalculator!!.calculate(cartValue)
                .add(deliveryDistanceCalculator!!.calculate(deliveryDistance))
                .add(cartCapacityCalculator!!.calculate(numberOfItems))
    }
}