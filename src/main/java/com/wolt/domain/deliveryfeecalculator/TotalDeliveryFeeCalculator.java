package com.wolt.domain.deliveryfeecalculator;

import lombok.AllArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
class TotalDeliveryFeeCalculator {

    private final CartTotalCalculator cartTotalCalculator;
    private final DeliveryDistanceCalculator deliveryDistanceCalculator;
    private final CartCapacityCalculator cartCapacityCalculator;
    private final RushHoursCalculator rushHoursCalculator;
    private final FinalFeeValidator finalFeeValidator;

    BigInteger calculateTotal(OrderData orderData) {
        return finalFeeValidator
                .validate(rushHoursCalculator
                        .calculate(orderData.orderTime(),
                                calculateBaseDelivery(orderData.cartValue(),
                                        orderData.deliveryDistance(),
                                        orderData.numberOfItems())));
    }

    private BigInteger calculateBaseDelivery(BigInteger cartValue, BigInteger deliveryDistance, BigInteger numberOfItems) {
        return cartTotalCalculator.calculate(cartValue)
                .add(deliveryDistanceCalculator.calculate(deliveryDistance))
                .add(cartCapacityCalculator.calculate(numberOfItems));
    }
}
