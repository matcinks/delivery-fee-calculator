package com.wolt.domain.deliveryfeecalculator.dto;

import lombok.Builder;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Builder
public record OrderDataDto(BigInteger cartValue,
                           BigInteger deliveryDistance,
                           BigInteger numberOfItems,
                           ZonedDateTime orderTime) {
}
