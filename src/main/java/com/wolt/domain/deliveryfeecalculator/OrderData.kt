package com.wolt.domain.deliveryfeecalculator

import lombok.Builder
import java.math.BigInteger
import java.time.ZonedDateTime

@Builder
data class OrderData(val cartValue: BigInteger, val deliveryDistance: BigInteger, val numberOfItems: BigInteger, val orderTime: ZonedDateTime) {
    init {
        this.deliveryFee = deliveryFee
        cartValue = cartValue
        deliveryDistance = deliveryDistance
        numberOfItems = numberOfItems
        orderTime = orderTime
    }
}