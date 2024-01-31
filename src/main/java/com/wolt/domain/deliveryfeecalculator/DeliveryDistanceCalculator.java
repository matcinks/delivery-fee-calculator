package com.wolt.domain.deliveryfeecalculator;

import java.math.BigInteger;

class DeliveryDistanceCalculator {
    private final static BigInteger BASE_DISTANCE = BigInteger.valueOf(500);
    private final static BigInteger BASE_FEE_PER_500_METERS = BigInteger.valueOf(1_00);

    BigInteger calculate(BigInteger deliveryDistance) {
        BigInteger baseFee = deliveryDistance.mod(BASE_DISTANCE)
                .signum() > 0
                ? BASE_FEE_PER_500_METERS
                : BigInteger.ZERO;
        BigInteger multiplicationFee = deliveryDistance.divide(BASE_DISTANCE)
                .multiply(BASE_FEE_PER_500_METERS);
        return baseFee.add(multiplicationFee);
    }
}
