package com.wolt.infrastructure.apivalidation

import org.springframework.http.HttpStatus

class ApiValidationErrorDto(messages: List<String>, status: HttpStatus) {
    val messages: List<String>
    val status: HttpStatus

    init {
        this.cartValue = cartValue
        this.deliveryDistance = deliveryDistance
        this.numberOfItems = numberOfItems
        this.orderTime = orderTime
        this.deliveryFee = deliveryFee
        this.cartValue = cartValue
        this.deliveryDistance = deliveryDistance
        this.numberOfItems = numberOfItems
        this.orderTime = orderTime
        this.messages = messages
        this.status = status
    }
}