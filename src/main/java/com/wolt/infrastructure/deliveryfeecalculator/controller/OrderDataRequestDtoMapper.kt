package com.wolt.infrastructure.deliveryfeecalculator.controller

import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto
import java.math.BigInteger

object OrderDataRequestDtoMapper {
    fun mapFromOrderDataRequestDto(requestDto: OrderDataRequestDto?): OrderDataDto {
        return OrderDataDto.builder()
                .cartValue(BigInteger.valueOf(requestDto!!.cartValue!!.toLong()))
                .deliveryDistance(BigInteger.valueOf(requestDto.deliveryDistance!!.toLong()))
                .numberOfItems(BigInteger.valueOf(requestDto.numberOfItems!!.toLong()))
                .orderTime(requestDto.time)
                .build()
    }
}