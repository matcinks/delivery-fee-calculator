package com.wolt.domain.deliveryfeecalculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryFeeCalculatorFacadeTest {

    @Test
    void should_add_remaining_surcharge_when_order_total_is_lower_than_10() {
        // given
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorFacade();
        // when
        int calculatedFee = deliveryFeeCalculatorFacade.calculateDeliveryFee(900, 5, 5, "2024-01-15T13:00:00Z");
        // then
        assertThat(calculatedFee).isEqualTo(102);
    }

    @Test
    void should_not_add_remaining_surcharge_when_order_total_is_greater_or_equals_10() {
        // given
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorFacade();
        // when
        int calculatedFee = deliveryFeeCalculatorFacade.calculateDeliveryFee(1000, 5, 5, "2024-01-15T13:00:00Z");
        // then
        assertThat(calculatedFee).isEqualTo(2);
    }

    @Test
    void should_add_2e_surcharge_when_delivery_distance_is_lower_or_equal_1km() {
        // given
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorFacade();
        // when
        int calculatedFee = deliveryFeeCalculatorFacade.calculateDeliveryFee(1000, 999, 5, "2024-01-15T13:00:00Z");
        // then
        assertThat(calculatedFee).isEqualTo(2);
    }

    @Test
    void should_add_4e_surcharge_when_delivery_distance_is_greater_than_1km_and_lower_than_2km() {
        // given
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorFacade();
        // when
        int calculatedFee = deliveryFeeCalculatorFacade.calculateDeliveryFee(1000, 1999, 5, "2024-01-15T13:00:00Z");
        // then
        assertThat(calculatedFee).isEqualTo(4);
    }

}