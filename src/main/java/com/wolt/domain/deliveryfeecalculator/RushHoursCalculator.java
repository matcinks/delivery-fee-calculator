package com.wolt.domain.deliveryfeecalculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;

class RushHoursCalculator {

    private static final DayOfWeek RUSH_DAY = DayOfWeek.FRIDAY;
    private static final LocalTime RUSH_TIME_BEGIN = LocalTime.of(14, 59, 59);
    private static final LocalTime RUSH_TIME_END = LocalTime.of(19, 0, 1);
    private static final BigDecimal INCREASE_RATE = BigDecimal.valueOf(1.2);

    BigInteger calculate(ZonedDateTime orderDate, BigInteger deliveryFee) {
        if (!areRushHours(orderDate)) {
            return deliveryFee;
        }
        BigDecimal increasedFee = new BigDecimal(deliveryFee).multiply(INCREASE_RATE)
                .setScale(0, RoundingMode.DOWN);
        return increasedFee.toBigInteger();
    }

    private boolean areRushHours(ZonedDateTime orderDate) {
        return orderDate.getDayOfWeek().equals(RUSH_DAY)
                && orderDate.toLocalTime().isAfter(RUSH_TIME_BEGIN)
                && orderDate.toLocalTime().isBefore(RUSH_TIME_END);
    }
}
