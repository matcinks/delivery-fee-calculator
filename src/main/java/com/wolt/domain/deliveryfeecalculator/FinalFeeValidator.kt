package com.wolt.domain.deliveryfeecalculator;

import java.math.BigInteger;

class FinalFeeValidator {
    private static final BigInteger MAX_DELIVERY_FEE = BigInteger.valueOf(15_00);

    BigInteger validate(BigInteger deliveryFee) {
        if (isDeliveryFeeMoreThanMax(deliveryFee)) {
            return MAX_DELIVERY_FEE;
        }
        return deliveryFee;
    }

    private boolean isDeliveryFeeMoreThanMax(BigInteger deliveryFee) {
        return deliveryFee.compareTo(MAX_DELIVERY_FEE) > 0;
    }
}
