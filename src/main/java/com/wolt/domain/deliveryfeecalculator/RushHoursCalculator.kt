package com.wolt.domain.deliveryfeecalculator

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime

internal class RushHoursCalculator {
    fun calculate(orderDate: ZonedDateTime, deliveryFee: BigInteger): BigInteger {
        if (!areRushHours(orderDate)) {
            return deliveryFee
        }
        val increasedFee = BigDecimal(deliveryFee).multiply(INCREASE_RATE)
            .setScale(0, RoundingMode.DOWN)
        return increasedFee.toBigInteger()
    }

    private fun areRushHours(orderDate: ZonedDateTime): Boolean {
        return (orderDate.dayOfWeek == RUSH_DAY && orderDate.toLocalTime().isAfter(RUSH_TIME_BEGIN)
                && orderDate.toLocalTime().isBefore(RUSH_TIME_END))
    }

    companion object {
        private val RUSH_DAY = DayOfWeek.FRIDAY
        private val RUSH_TIME_BEGIN = LocalTime.of(14, 59, 59)
        private val RUSH_TIME_END = LocalTime.of(19, 0, 1)
        private val INCREASE_RATE = BigDecimal.valueOf(1.2)
    }
}