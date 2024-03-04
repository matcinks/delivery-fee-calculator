package com.delivery.domain.deliveryfeecalculator

import com.delivery.domain.deliveryfeecalculator.dto.OrderDataDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.math.BigInteger
import java.time.DayOfWeek
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.TemporalAdjusters

class DeliveryFeeCalculatorFacadeTest {
    private var deliveryFeeCalculatorFacade: DeliveryFeeCalculatorFacade =
        DeliveryFeeCalculatorConfiguration().deliveryFeeCalculatorFacade()
    private var orderDate = ZonedDateTime.now(ZoneId.of("UTC"))

    @DisplayName("Should add remaining surcharge of 1 euro cent when order total is lower than 10 euro and add 1 euro delivery fee for delivery distance lower than 500 meters")
    @Test
    fun should_add_remaining_surcharge_when_order_total_is_lower_than_required_and_add_distance_delivery_fee() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(9_99)
        val deliveryDistanceInMeters = BigInteger.valueOf(499)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf((1 + 1_00).toLong())
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should not add remaining surcharge when order total is greater or equals 10 euro and add 2 euro delivery fee when delivery distance is 1000 meters")
    @Test
    fun should_not_add_remaining_surcharge_when_order_total_is_greater_or_equals_required_and_add_distance_delivery_fee() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(1_000)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf(2_00)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should add 3 euro delivery fee when delivery distance is exactly 1499 meters and there are no additional surcharges")
    @Test
    fun should_add_distance_delivery_fee_when_delivery_distance_is_1499_meters_and_there_are_no_additional_surcharges() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(1_499)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf(3_00)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should return 3 euro delivery fee when delivery distance is exactly 1500 meters and there are no additional surcharges")
    @Test
    fun should_return_distance_delivery_fee_when_delivery_distance_is_1500_meters_and_there_are_no_additional_surcharges() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(1_500)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf(3_00)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should return 4 euro delivery fee when delivery distance is exactly 1501 meters and there are no additional surcharges")
    @Test
    fun should_return_distance_delivery_fee_when_delivery_distance_is_1501_meters_and_there_are_no_additional_surcharges() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(1_501)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf(4_00)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should return 1 euro delivery fee when delivery distance is exactly 500 meters and there are no additional surcharges")
    @Test
    fun should_return_distance_delivery_fee_when_delivery_distance_is_500_meters_and_there_are_no_additional_surcharges() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf(1_00)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should add 50 cents delivery fee for one item above limit and one euro delivery distance fee and there are no additional surcharges")
    @Test
    fun should_add_items_above_limit_delivery_fee_for_one_item_above_limit_and_distance_delivery_fee_and_there_are_no_additional_surcharges() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(5)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf((50 + 1_00).toLong())
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should add 3 euro delivery fee for 6 items above limit and 1 euro delivery distance fee and there are no additional surcharges")
    @Test
    fun should_add_3_euro_delivery_fee_for_6_items_above_limit_and_one_euro_delivery_distance_fee_and_there_are_no_additional_surcharges() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(10)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf((6 * 50 + 1_00).toLong())
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should add 5 euro delivery fee for 10 items above limit and bulk surcharge and one euro delivery distance fee and there are no additional surcharges")
    @Test
    fun should_add_items_above_limit_delivery_fee_and_bulk_surcharge_and_distance_delivery_fee_and_there_are_no_additional_surcharges() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(14)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf((10 * 50 + 1_20 + 1_00).toLong())
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should increase by 20 percent total delivery fee during beginning of rush hours")
    @Test
    fun should_increase_total_delivery_fee_during_beginning_of_rush_hours() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
            .withHour(15)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigDecimal.valueOf(1_00 * 1.20).toBigInteger()
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should increase by 20 percent total delivery fee during rush hours")
    @Test
    fun should_increase_total_delivery_fee_during_rush_hours() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
            .withHour(16)
            .withMinute(30)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigDecimal.valueOf(1_00 * 1.20).toBigInteger()
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should increase by 20 percent total delivery fee during end of rush hours")
    @Test
    fun should_increase_total_delivery_fee_during_end_of_rush_hours() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
            .withHour(19)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigDecimal.valueOf(1_00 * 1.20).toBigInteger()
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should not increase total delivery fee during same time window as rush hours but in a different week day")
    @Test
    fun should_not_increase_total_delivery_fee_during_same_time_as_rush_hours_but_in_different_day() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(4)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(15)
            .withMinute(30)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf(1_00)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should return 15 euros total delivery fee when calculated total delivery fee is above limit")
    @Test
    fun should_return_maximum_delivery_fee_when_calculated_total_delivery_fee_is_above_limit() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(100)
        val deliveryDistanceInMeters = BigInteger.valueOf(5_000)
        val numberOfItemsInCart = BigInteger.valueOf(20)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.valueOf(15_00)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should return free delivery fee when total cart is equal to 200 euro")
    @Test
    fun should_return_free_delivery_fee_when_total_cart_is_equal_free_delivery_limit() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(200_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(10)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
            .withHour(19)
            .withMinute(30)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.ZERO
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should return free delivery fee when total cart is above 200 euros")
    @Test
    fun should_return_free_delivery_fee_when_total_cart_is_above_free_delivery_limit() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(200_01)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(10)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        // then
        val expectedDeliveryFee = BigInteger.ZERO
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }

    @DisplayName("Should throw exception when cart value is zero")
    @Test
    fun should_throw_exception_when_cart_value_is_zero() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(0)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(3)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Cart value must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when cart value is negative number")
    @Test
    fun should_throw_exception_when_cart_value_is_negative_number() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(-100)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(3)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Cart value must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when cart value is null")
    @Test
    fun should_throw_exception_when_cart_value_is_null() {
        // given
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(3)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = null,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Cart value must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when delivery distance is zero")
    @Test
    fun should_throw_exception_when_delivery_distance_is_zero() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(0)
        val numberOfItemsInCart = BigInteger.valueOf(3)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Delivery distance must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when delivery distance value is negative number")
    @Test
    fun should_throw_exception_when_delivery_distance_is_negative_number() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(-500)
        val numberOfItemsInCart = BigInteger.valueOf(3)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Delivery distance must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when delivery distance value is null")
    @Test
    fun should_throw_exception_when_delivery_distance_is_null() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val numberOfItemsInCart = BigInteger.valueOf(3)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = null,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Delivery distance must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when number of items is zero")
    @Test
    fun should_throw_exception_when_number_of_items_is_zero() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(0)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Number of items must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when number of items is negative number")
    @Test
    fun should_throw_exception_when_number_of_items_is_negative_number() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(-3)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Number of items must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when number of items is null")
    @Test
    fun should_throw_exception_when_number_of_items_is_null() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        orderDate = orderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY))
            .withHour(13)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = null,
            orderTime = orderDate
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Number of items must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }

    @DisplayName("Should throw exception when order date is null")
    @Test
    fun should_throw_exception_when_order_date_is_null() {
        // given
        val cartValueInEuroCents = BigInteger.valueOf(10_00)
        val deliveryDistanceInMeters = BigInteger.valueOf(500)
        val numberOfItemsInCart = BigInteger.valueOf(3)
        val orderDataDto = OrderDataDto(
            cartValue = cartValueInEuroCents,
            deliveryDistance = deliveryDistanceInMeters,
            numberOfItems = numberOfItemsInCart,
            orderTime = null
        )
        // when
        // then
        assertThrows<OrderDataNotValidException>("Number of items must not be null or negative") {
            deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        }
    }
}