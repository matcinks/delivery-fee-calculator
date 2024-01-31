package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;

import java.math.BigInteger;
import java.time.ZonedDateTime;

class OrderDataDtoValidator {

    private static final String NULL_OR_NEGATIVE_MESSAGE = " must not be null or negative";

    static void validate(OrderDataDto orderDataDto) {
        validateNonNegative(orderDataDto.cartValue(), "Cart value");
        validateNonNegative(orderDataDto.deliveryDistance(), "Delivery distance");
        validateNonNegative(orderDataDto.numberOfItems(), "Number of items");
        validateNotNull(orderDataDto.orderTime());
    }

    private static void validateNonNegative(BigInteger value, String fieldName) {
        if (value == null || value.compareTo(BigInteger.ZERO) <= 0) {
            throw new OrderDataNotValidException(fieldName + NULL_OR_NEGATIVE_MESSAGE);
        }
    }

    private static void validateNotNull(ZonedDateTime dateTime) {
        if (dateTime == null) {
            throw new OrderDataNotValidException("Order time must not be null");
        }
    }
}
