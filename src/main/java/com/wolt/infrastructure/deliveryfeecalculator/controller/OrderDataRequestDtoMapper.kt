package com.wolt.infrastructure.deliveryfeecalculator.controller

import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto

object OrderDataRequestDtoMapper {
    fun mapFromOrderDataRequestDto(requestDto: OrderDataRequestDto): OrderDataDto {
        return OrderDataDto(
            cartValue = requestDto.cartValue?.toBigInteger(),
            deliveryDistance = requestDto.deliveryDistance?.toBigInteger(),
            numberOfItems = requestDto.numberOfItems?.toBigInteger(),
            orderTime = requestDto.time
        )
    }
}