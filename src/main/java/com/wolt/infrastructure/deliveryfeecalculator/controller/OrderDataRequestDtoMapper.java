package com.wolt.infrastructure.deliveryfeecalculator.controller;

import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;

import java.math.BigInteger;

public class OrderDataRequestDtoMapper {

    static OrderDataDto mapFromOrderDataRequestDto(OrderDataRequestDto requestDto){
        return OrderDataDto.builder()
                .cartValue(BigInteger.valueOf(requestDto.cartValue()))
                .deliveryDistance(BigInteger.valueOf(requestDto.deliveryDistance()))
                .numberOfItems(BigInteger.valueOf(requestDto.numberOfItems()))
                .orderTime(requestDto.time())
                .build();
    }

}
