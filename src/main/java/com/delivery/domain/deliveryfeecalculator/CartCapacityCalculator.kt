package com.delivery.domain.deliveryfeecalculator

import java.math.BigInteger

internal class CartCapacityCalculator {
    fun calculate(numberOfItems: BigInteger): BigInteger {
        if (!isAmountOfItemsGreaterThanFour(numberOfItems)) {
            return BigInteger.ZERO
        }
        val deliveryFee = numberOfItems.subtract(MAX_NUMBER_OF_ITEMS_WITHOUT_FEE)
            .multiply(BASE_SURCHARGE)
        return if (isAmountOfItemsGreaterThanTwelve(numberOfItems)) {
            deliveryFee.add(BULK_FEE)
        } else deliveryFee
    }

    private fun isAmountOfItemsGreaterThanFour(numberOfItems: BigInteger): Boolean {
        return numberOfItems > MAX_NUMBER_OF_ITEMS_WITHOUT_FEE
    }

    private fun isAmountOfItemsGreaterThanTwelve(numberOfItems: BigInteger): Boolean {
        return numberOfItems > MAX_NUMBER_OF_ITEMS_WITHOUT_BULK_FEE
    }

    companion object {
        private val MAX_NUMBER_OF_ITEMS_WITHOUT_FEE = BigInteger.valueOf(4)
        private val MAX_NUMBER_OF_ITEMS_WITHOUT_BULK_FEE = BigInteger.valueOf(12)
        private val BASE_SURCHARGE = BigInteger.valueOf(50)
        private val BULK_FEE = BigInteger.valueOf(120)
    }
}