package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger

internal class DeliveryDistanceCalculator {
    fun calculate(deliveryDistance: BigInteger): BigInteger {
        if (isBaseDistance(deliveryDistance)) {
            return BASE_FEE_PER_500_METERS
        }
        val deliveryFee = deliveryDistance.divide(BASE_DISTANCE)
            .multiply(BASE_FEE_PER_500_METERS)
        return if (isTotalDistanceIncluded(deliveryDistance)) {
            deliveryFee
        } else deliveryFee.add(BASE_FEE_PER_500_METERS)
    }

    private fun isBaseDistance(deliveryDistance: BigInteger): Boolean {
        return deliveryDistance <= BASE_DISTANCE
    }

    private fun isTotalDistanceIncluded(deliveryDistance: BigInteger): Boolean {
        return deliveryDistance.remainder(BASE_FEE_PER_500_METERS) <= BigInteger.ZERO
    }

    companion object {
        private val BASE_DISTANCE = BigInteger.valueOf(500)
        private val BASE_FEE_PER_500_METERS = BigInteger.valueOf(1_00)
    }
}