package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger

internal class FreeDeliveryCalculator {
    fun isDeliveryFree(cartValue: BigInteger): Boolean {
        return cartValue.compareTo(FREE_DELIVERY_LIMIT) >= 0
    }

    companion object {
        private val FREE_DELIVERY_LIMIT = BigInteger.valueOf(20000)
    }
}