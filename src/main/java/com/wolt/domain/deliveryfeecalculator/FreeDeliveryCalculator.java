package com.wolt.domain.deliveryfeecalculator;

import java.math.BigInteger;

class FreeDeliveryCalculator {
    private static final BigInteger FREE_DELIVERY_LIMIT = BigInteger.valueOf(200_00);
    boolean isDeliveryFree(BigInteger cartValue) {
        return cartValue.compareTo(FREE_DELIVERY_LIMIT) >= 0;
    }
}
