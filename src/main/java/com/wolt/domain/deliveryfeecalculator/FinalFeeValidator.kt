package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger

internal class FinalFeeValidator {
    fun validate(deliveryFee: BigInteger?): BigInteger? {
        return if (isDeliveryFeeMoreThanMax(deliveryFee)) {
            MAX_DELIVERY_FEE
        } else deliveryFee
    }

    private fun isDeliveryFeeMoreThanMax(deliveryFee: BigInteger?): Boolean {
        return deliveryFee!!.compareTo(MAX_DELIVERY_FEE) > 0
    }

    companion object {
        private val MAX_DELIVERY_FEE = BigInteger.valueOf(1500)
    }
}