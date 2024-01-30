package com.wolt.infrastructure.deliveryfeecalculator.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrderDataRequestDto(
        @JsonProperty("cart_value")
        @NotNull(message = "{cart.value.not.null}")
        Integer cartValue,
        @JsonProperty("delivery_distance")
        @NotNull(message = "{delivery.distance.not.null}")
        Integer deliveryDistance,
        @JsonProperty("number_of_items")
        @NotNull(message = "{number.of.items.not.null}")
        Integer numberOfItems,
        @NotEmpty(message = "{order.data.not.empty}")
        String time) {
}
