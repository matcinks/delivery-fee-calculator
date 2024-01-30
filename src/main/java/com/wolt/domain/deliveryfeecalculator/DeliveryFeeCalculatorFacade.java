package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto;
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class DeliveryFeeCalculatorFacade {

    private static final BigInteger FREE_DELIVERY_LIMIT = BigInteger.valueOf(200_00);
    private static final BigInteger NO_DELIVERY_FEE = BigInteger.ZERO;
    private final CartCapacityCalculator cartCapacityCalculator;
    private final CartTotalCalculator cartTotalCalculator;
    private final DeliveryDistanceCalculator deliveryDistanceCalculator;
    private final RushHoursValidator rushHoursValidator;
    private final FinalFeeValidator finalFeeValidator;

    public DeliveryFeeCalculatorResponseDto calculateDeliveryFee(OrderDataDto orderDataDto) {
        OrderData orderData = OrderData.builder()
                .cartValue(orderDataDto.cartValue())
                .deliveryDistance(orderDataDto.deliveryDistance())
                .numberOfItems(orderDataDto.numberOfItems())
                .orderTime(orderDataDto.orderTime())
                .build();

        BigInteger cartValue = orderData.cartValue();
        if (isCartTotalGreaterOrEqualFreeDeliveryLimit(cartValue)) {
            return DeliveryFeeCalculatorResponseDto.builder()
                    .deliveryFee(NO_DELIVERY_FEE)
                    .build();
        }
        BigInteger deliveryDistance = orderData.deliveryDistance();
        BigInteger numberOfItems = orderData.numberOfItems();
        ZonedDateTime orderDate = ZonedDateTime.parse(orderData.orderTime(), DateTimeFormatter.ISO_DATE_TIME);

        BigInteger deliveryFee = calculateBaseDelivery(cartValue, deliveryDistance, numberOfItems);
        deliveryFee = rushHoursValidator.calculate(orderDate, deliveryFee);
        deliveryFee = finalFeeValidator.validate(deliveryFee);

        return DeliveryFeeCalculatorResponseDto.builder()
                .deliveryFee(deliveryFee)
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
