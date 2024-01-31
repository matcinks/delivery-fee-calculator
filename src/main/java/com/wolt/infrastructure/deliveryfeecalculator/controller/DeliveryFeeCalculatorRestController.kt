package com.wolt.infrastructure.deliveryfeecalculator.controller;

import com.wolt.domain.deliveryfeecalculator.DeliveryFeeCalculatorFacade;
import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto;
import com.wolt.domain.deliveryfeecalculator.dto.OrderDataDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@Log4j2
@AllArgsConstructor
public class DeliveryFeeCalculatorRestController {

    private final DeliveryFeeCalculatorFacade deliveryFeeCalculatorFacade;

    @PostMapping("/deliveryFee")
    public ResponseEntity<DeliveryFeeCalculatorResponseDto> calculateDeliveryFee(@RequestBody @Valid OrderDataRequestDto requestDto) {
        OrderDataDto orderDataDto = OrderDataRequestDtoMapper.mapFromOrderDataRequestDto(requestDto);
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = deliveryFeeCalculatorFacade.calculateDeliveryFee(orderDataDto);
        return ResponseEntity.ok(deliveryFeeCalculatorResponseDto);
    }
}
