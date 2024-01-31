package com.wolt.domain.deliveryfeecalculator

import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto

object OrderDataMapper {
    fun mapFromOrderDataDto(orderDataDto: OrderDataDto?): OrderData {
        return OrderData.builder()
                .cartValue(orderDataDto!!.cartValue)
                .deliveryDistance(orderDataDto.deliveryDistance)
                .numberOfItems(orderDataDto.numberOfItems)
                .orderTime(orderDataDto.orderTime)
                .build()
    }
}