package com.wolt.domain.deliveryfeecalculator

import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto
import java.math.BigInteger
import java.time.ZonedDateTime

internal object OrderDataDtoValidator {
    private const val NULL_OR_NEGATIVE_MESSAGE = " must not be null or negative"
    fun validate(orderDataDto: OrderDataDto?) {
        validateNonNegative(orderDataDto!!.cartValue, "Cart value")
        validateNonNegative(orderDataDto.deliveryDistance, "Delivery distance")
        validateNonNegative(orderDataDto.numberOfItems, "Number of items")
        validateNotNull(orderDataDto.orderTime)
    }

    private fun validateNonNegative(value: BigInteger?, fieldName: String) {
        if (value == null || value.compareTo(BigInteger.ZERO) <= 0) {
            throw OrderDataNotValidException(fieldName + NULL_OR_NEGATIVE_MESSAGE)
        }
    }

    private fun validateNotNull(dateTime: ZonedDateTime?) {
        if (dateTime == null) {
            throw OrderDataNotValidException("Order time must not be null")
        }
    }
}