package com.wolt.infrastructure.deliveryfeecalculator.controller

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime

class OrderDataRequestDto(@field:JsonProperty("cart_value") @param:JsonProperty("cart_value") val cartValue: @NotNull(message = "{cart.value.not.null}") @Positive(message = "{cart.value.positive}") Int?, @field:JsonProperty("delivery_distance") @param:JsonProperty("delivery_distance") val deliveryDistance: @NotNull(message = "{delivery.distance.not.null}") @Positive(message = "{delivery.distance.value.positive}") Int?, @field:JsonProperty("number_of_items") @param:JsonProperty("number_of_items") val numberOfItems: @NotNull(message = "{number.of.items.not.null}") @Positive(message = "{number.of.items.positive}") Int?, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) time: @NotNull(message = "{order.time.not.null}") ZonedDateTime?) {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val time: @NotNull(message = "{order.time.not.null}") ZonedDateTime?

    init {
        this.orderTime = orderTime
        this.deliveryFee = deliveryFee
        cartValue = cartValue
        deliveryDistance = deliveryDistance
        numberOfItems = numberOfItems
        this.orderTime = orderTime
        this.messages = messages
        this.status = status
        cartValue = cartValue
        deliveryDistance = deliveryDistance
        numberOfItems = numberOfItems
        this.time = time
    }
}