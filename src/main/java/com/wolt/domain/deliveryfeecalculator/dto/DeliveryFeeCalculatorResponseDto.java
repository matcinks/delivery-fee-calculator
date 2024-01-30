package com.wolt.domain.deliveryfeecalculator.dto;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record DeliveryFeeCalculatorResponseDto(BigInteger deliveryFee) {
}
