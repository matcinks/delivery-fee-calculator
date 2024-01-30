package com.wolt.domain.deliveryfeecalculator;

import lombok.Builder;

import java.math.BigInteger;

@Builder
record OrderData(BigInteger cartValue,
        BigInteger deliveryDistance,
        BigInteger numberOfItems,
        String orderTime) {
}
