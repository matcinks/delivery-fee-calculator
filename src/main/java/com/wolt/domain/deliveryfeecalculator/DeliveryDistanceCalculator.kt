package com.wolt.domain.deliveryfeecalculator;

import java.math.BigInteger;

class DeliveryDistanceCalculator {
    private final static BigInteger BASE_DISTANCE = BigInteger.valueOf(500);
    private final static BigInteger BASE_FEE_PER_500_METERS = BigInteger.valueOf(1_00);

    BigInteger calculate(BigInteger deliveryDistance) {
        if (isBaseDistance(deliveryDistance)) {
            return BASE_FEE_PER_500_METERS;
        }
        BigInteger deliveryFee = deliveryDistance.divide(BASE_DISTANCE)
                .multiply(BASE_FEE_PER_500_METERS);
        if (isTotalDistanceIncluded(deliveryDistance)) {
            return deliveryFee;
        }
        return deliveryFee.add(BASE_FEE_PER_500_METERS);
    }

    private boolean isBaseDistance(BigInteger deliveryDistance) {
        return deliveryDistance.compareTo(BASE_DISTANCE) <= 0;
    }

    private boolean isTotalDistanceIncluded(BigInteger deliveryDistance) {
        return deliveryDistance.remainder(BASE_FEE_PER_500_METERS)
                .compareTo(BigInteger.ZERO) <= 0;
    }
}
