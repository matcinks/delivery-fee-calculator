package com.delivery.domain.deliveryfeecalculator.dto

import java.math.BigInteger
import java.time.ZonedDateTime

data class OrderDataDto(
    val cartValue: BigInteger?,
    val deliveryDistance: BigInteger?,
    val numberOfItems: BigInteger?,
    val orderTime: ZonedDateTime?
)