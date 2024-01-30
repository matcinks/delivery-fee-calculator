package com.wolt.domain.deliveryfeecalculator.dto;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record OrderDataDto(BigInteger cartValue,
                           BigInteger deliveryDistance,
                           BigInteger numberOfItems,
                           String orderTime) {
}
