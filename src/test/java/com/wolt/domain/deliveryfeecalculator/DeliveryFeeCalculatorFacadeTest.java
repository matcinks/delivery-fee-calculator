package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto;
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryFeeCalculatorFacadeTest {

    @Test
    void should_add_remaining_surcharge_when_order_total_is_lower_than_10_euro_and_add_1_euro_delivery_fee_for_delivery_distance_lower_than_500_meters() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(9_99);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(499);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(1 + 1_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_not_add_remaining_surcharge_when_order_total_is_greater_or_equals_10_and_add_2_euro_deliver_fee_when_delivery_distance_is_1000_meters() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1000);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(2_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_add_3_euro_delivery_fee_when_delivery_distance_is_1499_meters_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1499);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(3_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_return_3_euro_delivery_fee_when_delivery_distance_is_1500_meters_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(3_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_return_4_euro_delivery_fee_when_delivery_distance_is_1501_meters_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1501);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();;
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(4_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_add_50_cents_delivery_fee_for_one_item_above_limit_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(5);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(50 + 1_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_add_3_euro_delivery_fee_for_6_items_above_limit_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(10);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf((6 * 50) + 1_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_add_5_euro_delivery_fee_for_10_items_above_limit_and_bulk_surcharge_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(14);
        String orderDate = "2024-01-19T14:59:59Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf((10 * 50) + 1_20 + 1_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_increase_by_20_percent_delivery_fee_during_beginning_of_rush_hours_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T15:00:00Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigDecimal.valueOf(1_00 * 1.20).toBigInteger();
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_increase_by_20_percent_delivery_fee_during_rush_hours_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T16:30:00Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigDecimal.valueOf(1_00 * 1.20).toBigInteger();
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_increase_by_20_percent_delivery_fee_during_end_of_rush_hours_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        String orderDate = "2024-01-19T19:00:00Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigDecimal.valueOf(1_00 * 1.20).toBigInteger();
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_return_15_euros_delivery_fee_when_total_delivery_fee_is_above_limit() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(1_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(5000);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(20);
        String orderDate = "2024-01-19T13:00:00Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(15_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_return_free_delivery_fee_when_total_cart_is_equal_to_200_euro() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(200_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(10);
        String orderDate = "2024-01-19T13:00:00Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.ZERO;
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @Test
    void should_return_free_delivery_fee_when_total_cart_is_above_200_euro() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(200_01);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(10);
        String orderDate = "2024-01-19T13:00:00Z";
        DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.ZERO;
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

}