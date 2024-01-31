package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger

internal class FinalFeeValidator {
    fun validate(deliveryFee: BigInteger): BigInteger {
        return if (isDeliveryFeeMoreThanMax(deliveryFee)) {
            MAX_DELIVERY_FEE
        } else deliveryFee
    }

    private fun isDeliveryFeeMoreThanMax(deliveryFee: BigInteger): Boolean {
        return deliveryFee > MAX_DELIVERY_FEE
    }

    companion object {
        private val MAX_DELIVERY_FEE = BigInteger.valueOf(1500)
    }
}