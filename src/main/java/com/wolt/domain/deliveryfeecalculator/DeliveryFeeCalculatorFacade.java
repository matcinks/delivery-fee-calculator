package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto;
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;
import lombok.AllArgsConstructor;

import java.math.BigInteger;

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
        OrderDataDtoValidator.validate(orderDataDto);
        OrderData orderData = OrderDataMapper.mapFromOrderDataDto(orderDataDto);

        if (isDeliveryFree(orderData.cartValue())) {
            return DeliveryFeeCalculatorResponseDto.builder()
                    .deliveryFee(NO_DELIVERY_FEE)
                    .build();
        }

        return DeliveryFeeCalculatorResponseDto.builder()
                .deliveryFee(calculateNoFreeDeliverySomething(orderData))
                .build();
    }

    // individual class with new method (+ creation of this class inside Configuration class)
    private boolean isDeliveryFree(BigInteger cartValue) {
        return cartValue.compareTo(FREE_DELIVERY_LIMIT) >= 0;
    }

    // another individual class with calculate base delivery method + creation of this class inside Configuration class
//    private BigInteger calculateNoFreeDeliverySomething(OrderData orderData) {
//        BigInteger deliveryFee = calculateBaseDelivery(orderData.cartValue(), orderData.deliveryDistance(), orderData.numberOfItems());
//
//        deliveryFee = rushHoursValidator.calculate(orderData.orderTime(), deliveryFee);

//        deliveryFee = finalFeeValidator.validate(deliveryFee);
//        return deliveryFee;
//    }

    // second option of above code, there are no variables, only invocations
    private BigInteger calculateNoFreeDeliverySomething(OrderData orderData) {
        return finalFeeValidator
                .validate(rushHoursValidator
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
