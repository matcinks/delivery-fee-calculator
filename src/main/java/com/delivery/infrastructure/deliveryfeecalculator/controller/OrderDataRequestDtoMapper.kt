package com.delivery.infrastructure.deliveryfeecalculator.controller

import com.delivery.domain.deliveryfeecalculator.dto.OrderDataDto

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