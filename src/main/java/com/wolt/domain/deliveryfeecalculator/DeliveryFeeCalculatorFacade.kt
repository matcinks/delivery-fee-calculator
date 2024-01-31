package com.wolt.domain.deliveryfeecalculator

import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto

import java.math.BigInteger

class DeliveryFeeCalculatorFacade internal constructor(
    private val freeDeliveryCalculator: FreeDeliveryCalculator,
    private val deliveryFeeCalculator: TotalDeliveryFeeCalculator
) {
    fun calculateDeliveryFee(orderDataDto: OrderDataDto): DeliveryFeeCalculatorResponseDto {
        OrderDataDtoValidator.validate(orderDataDto)
        val orderData = OrderDataMapper.mapFromOrderDataDto(orderDataDto)
        return if (freeDeliveryCalculator.isDeliveryFree(orderData.cartValue)) {
            DeliveryFeeCalculatorResponseDto(deliveryFee = NO_DELIVERY_FEE)
        } else
            DeliveryFeeCalculatorResponseDto(deliveryFee = deliveryFeeCalculator.calculateTotal(orderData))
    }

    companion object {
        private val NO_DELIVERY_FEE = BigInteger.ZERO
    }
}