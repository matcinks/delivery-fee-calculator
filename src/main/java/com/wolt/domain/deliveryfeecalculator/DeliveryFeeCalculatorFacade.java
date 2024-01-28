package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.CalculatedDeliveryFeeDto;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DeliveryFeeCalculatorFacade {

    private static final BigInteger FREE_DELIVERY_LIMIT = BigInteger.valueOf(200_00);
    private static final BigInteger NO_DELIVERY_FEE = BigInteger.ZERO;
    private final CartCapacityCalculator cartCapacityCalculator = new CartCapacityCalculator();
    private final CartTotalCalculator cartTotalCalculator = new CartTotalCalculator();
    private final DeliveryDistanceCalculator deliveryDistanceCalculator = new DeliveryDistanceCalculator();
    private final RushHoursValidator rushHoursValidator = new RushHoursValidator();
    private final FinalFeeValidator finalFeeValidator = new FinalFeeValidator();

    public CalculatedDeliveryFeeDto calculateDeliveryFee(OrderData orderData) {
        BigInteger cartValue = orderData.cartValue();
        if (isCartTotalGreaterOrEqualFreeDeliveryLimit(cartValue)) {
            return CalculatedDeliveryFeeDto.builder()
                    .calculatedDeliveryFee(NO_DELIVERY_FEE)
                    .build();
        }
        BigInteger deliveryDistance = orderData.deliveryDistance();
        BigInteger numberOfItems = orderData.numberOfItems();
        ZonedDateTime orderDate = ZonedDateTime.parse(orderData.orderTime(), DateTimeFormatter.ISO_DATE_TIME);

        BigInteger deliveryFee = calculateBaseDelivery(cartValue, deliveryDistance, numberOfItems);
        deliveryFee = rushHoursValidator.calculate(orderDate, deliveryFee);
        deliveryFee = finalFeeValidator.validate(deliveryFee);

        return CalculatedDeliveryFeeDto.builder()
                .calculatedDeliveryFee(deliveryFee)
                .build();
    }

    private boolean isCartTotalGreaterOrEqualFreeDeliveryLimit(BigInteger cartValue) {
        return cartValue.compareTo(FREE_DELIVERY_LIMIT) >= 0;
    }

    private BigInteger calculateBaseDelivery(BigInteger cartValue, BigInteger deliveryDistance, BigInteger numberOfItems) {
        return cartTotalCalculator.calculate(cartValue)
                .add(deliveryDistanceCalculator.calculate(deliveryDistance))
                .add(cartCapacityCalculator.calculate(numberOfItems));
    }
}
