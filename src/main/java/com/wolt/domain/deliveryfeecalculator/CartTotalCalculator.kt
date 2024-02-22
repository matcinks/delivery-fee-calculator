package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger

internal class CartTotalCalculator {
    fun calculate(cartValue: BigInteger): BigInteger {
        val cartValueDifference = REQUIRED_CART_VALUE.subtract(cartValue)
        return cartValueDifference.max(NO_ADDITIONAL_FEE)
    }

    companion object {
        private val REQUIRED_CART_VALUE = BigInteger.valueOf(10_00)
        private val NO_ADDITIONAL_FEE = BigInteger.ZERO
    }
}