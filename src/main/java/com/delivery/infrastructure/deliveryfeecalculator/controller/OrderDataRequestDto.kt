package com.delivery.infrastructure.deliveryfeecalculator.controller

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime

data class OrderDataRequestDto(
    @field:JsonProperty("cart_value")
    @field:NotNull(message = "{cart.value.not.null}")
    @field:Positive(message = "{cart.value.positive}")
    val cartValue: Int? = 0,

    @field:JsonProperty("delivery_distance")
    @field:NotNull(message = "{delivery.distance.not.null}")
    @field:Positive(message = "{delivery.distance.positive}")
    val deliveryDistance: Int? = 0,

    @field:JsonProperty("number_of_items")
    @field:NotNull(message = "{number.of.items.not.null}")
    @field:Positive(message = "{number.of.items.positive}")
    val numberOfItems: Int? = 0,

    @field:NotNull(message = "{order.time.not.null}")
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val time: ZonedDateTime? = null,
)