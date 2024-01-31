package com.wolt.infrastructure.deliveryfeecalculator.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public record OrderDataRequestDto(
        @JsonProperty("cart_value")
        @NotNull(message = "{cart.value.not.null}")
        @Positive(message = "{cart.value.positive}")
        Integer cartValue,
        @JsonProperty("delivery_distance")
        @NotNull(message = "{delivery.distance.not.null}")
        @Positive(message = "{delivery.distance.value.positive}")
        Integer deliveryDistance,
        @JsonProperty("number_of_items")
        @NotNull(message = "{number.of.items.not.null}")
        @Positive(message = "{number.of.items.positive}")
        Integer numberOfItems,
        @NotNull(message = "{order.time.not.null}")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        ZonedDateTime time) {
}
