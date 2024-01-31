package com.wolt.domain.deliveryfeecalculator.dto

import lombok.Builder
import java.math.BigInteger
import java.time.ZonedDateTime

@Builder
@JvmRecord
data class OrderDataDto(val cartValue: BigInteger, val deliveryDistance: BigInteger, val numberOfItems: BigInteger, val orderTime: ZonedDateTime)