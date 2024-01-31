package com.wolt.domain.deliveryfeecalculator;

import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;

public class OrderDataMapper {

    static OrderData mapFromOrderDataDto(OrderDataDto orderDataDto) {
        return OrderData.builder()
                .cartValue(orderDataDto.cartValue())
                .deliveryDistance(orderDataDto.deliveryDistance())
                .numberOfItems(orderDataDto.numberOfItems())
                .orderTime(orderDataDto.orderTime())
                .build();
    }
}
