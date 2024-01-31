package com.wolt.domain.deliveryfeecalculator;

import java.math.BigInteger;

class CartTotalCalculator {
    private final static BigInteger REQUIRED_CART_VALUE = BigInteger.valueOf(10_00);
    private final static BigInteger NO_ADDITIONAL_FEE = BigInteger.ZERO;

    BigInteger calculate(BigInteger cartValue) {
        BigInteger cartValueDifference = REQUIRED_CART_VALUE.subtract(cartValue);
        return cartValueDifference.max(NO_ADDITIONAL_FEE);
    }
}
