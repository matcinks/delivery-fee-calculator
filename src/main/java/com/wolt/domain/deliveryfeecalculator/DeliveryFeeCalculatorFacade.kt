package com.wolt.domain.deliveryfeecalculator

import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto
import lombok.AllArgsConstructor
import java.math.BigInteger

@AllArgsConstructor
class DeliveryFeeCalculatorFacade {
    private val freeDeliveryCalculator: FreeDeliveryCalculator? = null
    private val deliveryFeeCalculator: TotalDeliveryFeeCalculator? = null
    fun calculateDeliveryFee(orderDataDto: OrderDataDto?): DeliveryFeeCalculatorResponseDto {
        OrderDataDtoValidator.validate(orderDataDto)
        val orderData = OrderDataMapper.mapFromOrderDataDto(orderDataDto)
        return if (freeDeliveryCalculator!!.isDeliveryFree(orderData!!.cartValue)) {
            DeliveryFeeCalculatorResponseDto(deliveryFee = NO_DELIVERY_FEE)
        } else DeliveryFeeCalculatorResponseDto.builder()
                .deliveryFee(deliveryFeeCalculator!!.calculateTotal(orderData))
                .build()
    }

    companion object {
        private val NO_DELIVERY_FEE = BigInteger.ZERO
    }
}