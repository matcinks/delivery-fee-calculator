package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto;
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;
import lombok.AllArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
public class DeliveryFeeCalculatorFacade {

    private static final BigInteger NO_DELIVERY_FEE = BigInteger.ZERO;
    private final FreeDeliveryCalculator freeDeliveryCalculator;
    private final TotalDeliveryFeeCalculator deliveryFeeCalculator;

    public DeliveryFeeCalculatorResponseDto calculateDeliveryFee(OrderDataDto orderDataDto) {
        OrderDataDtoValidator.validate(orderDataDto);
        OrderData orderData = OrderDataMapper.mapFromOrderDataDto(orderDataDto);
        if (freeDeliveryCalculator.isDeliveryFree(orderData.cartValue())) {
            return DeliveryFeeCalculatorResponseDto.builder()
                    .deliveryFee(NO_DELIVERY_FEE)
                    .build();
        }
        return DeliveryFeeCalculatorResponseDto.builder()
                .deliveryFee(deliveryFeeCalculator.calculateTotal(orderData))
                .build();
    }
}
