package com.wolt.domain.deliveryfeecalculator

import java.math.BigInteger
import java.time.ZonedDateTime

internal data class OrderData(
    val cartValue: BigInteger,
    val deliveryDistance: BigInteger,
    val numberOfItems: BigInteger,
    val orderTime: ZonedDateTime
)