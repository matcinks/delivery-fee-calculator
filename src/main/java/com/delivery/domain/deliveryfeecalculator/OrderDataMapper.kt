package com.delivery.domain.deliveryfeecalculator

import com.delivery.domain.deliveryfeecalculator.dto.OrderDataDto

internal object OrderDataMapper {
    internal fun mapFromOrderDataDto(orderDataDto: OrderDataDto): OrderData {
        return OrderData(
                cartValue = requireNotNull(orderDataDto.cartValue),
                deliveryDistance = requireNotNull(orderDataDto.deliveryDistance),
                numberOfItems = requireNotNull(orderDataDto.numberOfItems),
                orderTime = requireNotNull(orderDataDto.orderTime)
        )
    }
}