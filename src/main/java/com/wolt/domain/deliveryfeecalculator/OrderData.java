package com.wolt.domain.deliveryfeecalculator;

import lombok.Builder;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Builder
record OrderData(
        BigInteger cartValue,
        BigInteger deliveryDistance,
        BigInteger numberOfItems,
        ZonedDateTime orderTime) {
}
