package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger

internal class FreeDeliveryCalculator {
    fun isDeliveryFree(cartValue: BigInteger): Boolean {
        return cartValue >= FREE_DELIVERY_LIMIT
    }

    companion object {
        private val FREE_DELIVERY_LIMIT = BigInteger.valueOf(20000)
    }
}