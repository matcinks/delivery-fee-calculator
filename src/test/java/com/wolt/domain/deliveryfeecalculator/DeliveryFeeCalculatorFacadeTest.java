package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto;
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryFeeCalculatorFacadeTest {

    DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade = new DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade();

    @DisplayName("Should add remaining surcharge of 1 euro cent when order total is lower than 10 euro and add 1 euro delivery fee for delivery distance lower than 500 meters")
    @Test
    void should_add_remaining_surcharge_when_order_total_is_lower_than_required_and_add_distance_delivery_fee() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(9_99);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(499);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));

        ZonedDateTime orderDate2 = ZonedDateTime.now(ZoneId.of("UTC"));
        orderDate2.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
                .withHour(15)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

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

    @DisplayName("Should not add remaining surcharge when order total is greater or equals 10 euro and add 2 euro delivery fee when delivery distance is 1000 meters")
    @Test
    void should_not_add_remaining_surcharge_when_order_total_is_greater_or_equals_required_and_add_distance_delivery_fee() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1_000);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));
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

    @DisplayName("Should add 3 euro delivery fee when delivery distance is exactly 1499 meters and there are no additional surcharges")
    @Test
    void should_add_distance_delivery_fee_when_delivery_distance_is_1499_meters_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1_499);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));
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

    @DisplayName("Should return 3 euro delivery fee when delivery distance is exactly 1500 meters and there are no additional surcharges")
    @Test
    void should_return_distance_delivery_fee_when_delivery_distance_is_1500_meters_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1_500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));
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

    @DisplayName("Should return 4 euro delivery fee when delivery distance is exactly 1501 meters and there are no additional surcharges")
    @Test
    void should_return_distance_delivery_fee_when_delivery_distance_is_1501_meters_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(1501);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));
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

    @DisplayName("Should add 50 cents delivery fee for one item above limit and one euro delivery distance fee and there are no additional surcharges")
    @Test
    void should_add_items_above_limit_delivery_fee_for_one_item_above_limit_and_distance_delivery_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(5);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));
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

    @DisplayName("Should add 3 euro delivery fee for 6 items above limit and 1 euro delivery distance fee and there are no additional surcharges")
    @Test
    void should_add_3_euro_delivery_fee_for_6_items_above_limit_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(10);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));
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

    @DisplayName("Should add 5 euro delivery fee for 10 items above limit and bulk surcharge and one euro delivery distance fee and there are no additional surcharges")
    @Test
    void should_add_items_above_limit_delivery_fee_and_bulk_surcharge_and_distance_delivery_fee_and_there_are_no_additional_surcharges() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(14);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,14, 59, 59,0, ZoneId.of("UTC"));
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

    @DisplayName("Should increase by 20 percent total delivery fee during beginning of rush hours")
    @Test
    void should_increase_total_delivery_fee_during_beginning_of_rush_hours() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,15, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigDecimal expectedDeliveryFee = BigDecimal.valueOf(1_00 * 1.20);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee.toBigInteger());
    }

    @DisplayName("Should increase by 20 percent total delivery fee during rush hours")
    @Test
    void should_increase_total_delivery_fee_during_rush_hours() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,16, 30, 0,0, ZoneId.of("UTC"));
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

    @DisplayName("Should increase by 20 percent total delivery fee during end of rush hours")
    @Test
    void should_increase_total_delivery_fee_during_end_of_rush_hours() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,19, 0, 0,0, ZoneId.of("UTC"));
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

    @DisplayName("Should not increase total delivery fee during same time window as rush hours but in a different week day")
    @Test
    void should_not_increase_total_delivery_fee_during_same_time_as_rush_hours_but_in_different_day() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(4);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,18,15, 30, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        // then
        BigInteger expectedDeliveryFee = BigInteger.valueOf(1_00);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }

    @DisplayName("Should return 15 euros total delivery fee when calculated total delivery fee is above limit")
    @Test
    void should_return_maximum_delivery_fee_when_calculated_total_delivery_fee_is_above_limit() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(1_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(5_000);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(20);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
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

    @DisplayName("Should return free delivery fee when total cart is equal to 200 euro")
    @Test
    void should_return_free_delivery_fee_when_total_cart_is_equal_free_delivery_limit() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(200_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(10);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
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

    @DisplayName("Should return free delivery fee when total cart is above 200 euros")
    @Test
    void should_return_free_delivery_fee_when_total_cart_is_above_free_delivery_limit() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(200_01);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(10);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
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

    @DisplayName("Should throw exception when cart value is zero")
    @Test
    void should_throw_exception_when_cart_value_is_zero() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(0);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(3);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Cart value must not be null or negative");
    }

    @DisplayName("Should throw exception when cart value is negative number")
    @Test
    void should_throw_exception_when_cart_value_is_negative_number() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(-100);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(3);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Cart value must not be null or negative");
    }

    @DisplayName("Should throw exception when cart value is null")
    @Test
    void should_throw_exception_when_cart_value_is_null() {
        // given
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(3);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Cart value must not be null or negative");
    }

    @DisplayName("Should throw exception when delivery distance is zero")
    @Test
    void should_throw_exception_when_delivery_distance_is_zero() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(0);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(3);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Delivery distance must not be null or negative");
    }

    @DisplayName("Should throw exception when delivery distance value is negative number")
    @Test
    void should_throw_exception_when_delivery_distance_is_negative_number() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(-500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(3);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Delivery distance must not be null or negative");
    }

    @DisplayName("Should throw exception when delivery distance value is null")
    @Test
    void should_throw_exception_when_delivery_distance_is_null() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(3);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Delivery distance must not be null or negative");
    }


    @DisplayName("Should throw exception when number of items is zero")
    @Test
    void should_throw_exception_when_number_of_items_is_zero() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(0);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Number of items must not be null or negative");
    }

    @DisplayName("Should throw exception when number of items is negative number")
    @Test
    void should_throw_exception_when_number_of_items_is_negative_number() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(-3);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Number of items must not be null or negative");
    }

    @DisplayName("Should throw exception when number of items is null")
    @Test
    void should_throw_exception_when_number_of_items_is_null() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        ZonedDateTime orderDate = ZonedDateTime.of(2024, 1,19,13, 0, 0,0, ZoneId.of("UTC"));
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .orderTime(orderDate)
                .build();
        // when
        // then
        assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Number of items must not be null or negative");
    }

    @DisplayName("Should throw exception when order date is null")
    @Test
    void should_throw_exception_when_order_date_is_null() {
        // given
        BigInteger cartValueInEuroCents = BigInteger.valueOf(10_00);
        BigInteger deliveryDistanceInMeters = BigInteger.valueOf(500);
        BigInteger numberOfItemsInCart = BigInteger.valueOf(3);
        OrderDataDto orderDataDto = OrderDataDto.builder()
                .cartValue(cartValueInEuroCents)
                .deliveryDistance(deliveryDistanceInMeters)
                .numberOfItems(numberOfItemsInCart)
                .build();
        // when
        // then
        OrderDataNotValidException exception = assertThrows(OrderDataNotValidException.class, () ->  deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto), "Order time must not be null");
    }
}