package com.delivery.infrastructure.deliveryfeecalculator.controller

import com.delivery.domain.deliveryfeecalculator.DeliveryFeeCalculatorFacade
import com.delivery.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto
import jakarta.validation.Valid
import lombok.extern.log4j.Log4j2
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Log4j2
class DeliveryFeeCalculatorRestController(
    private val deliveryFeeCalculatorFacade: DeliveryFeeCalculatorFacade
) {
    @PostMapping("/delivery_fee")
    fun calculateDeliveryFee(@RequestBody requestDto: @Valid OrderDataRequestDto): ResponseEntity<DeliveryFeeCalculatorResponseDto?> {
        val orderDataDto = OrderDataRequestDtoMapper.mapFromOrderDataRequestDto(requestDto)
        val deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto)
        return ResponseEntity.ok(deliveryFeeCalculatorResponseDto)
    }
}