package com.wolt.feature

import com.fasterxml.jackson.module.kotlin.readValue
import com.wolt.BaseIntegrationTest
import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import java.math.BigInteger

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.assertj.core.api.Assertions.assertThat

class DeliveryFeeIntegrationTest : BaseIntegrationTest() {

    @Test
    fun shouldCalculateDeliveryFee() {
        // given
        // when
        val perform: ResultActions = mockMvc.perform(
            post("/deliveryFee")
                .content(
                    """
                        {
                        "cart_value": 790,
                        "delivery_distance": 2235,
                        "number_of_items": 4,
                        "time": "2024-01-15T13:00:00Z"
                        }
                    """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
        // then
        val mvcResult: MvcResult = perform.andExpect(status().isOk).andReturn()
        val json: String = mvcResult.response.contentAsString
        val deliveryFeeCalculatorResponseDto: DeliveryFeeCalculatorResponseDto =
            objectMapper.readValue(json)
        val expectedDeliveryFee = BigInteger.valueOf(710)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee).isEqualTo(expectedDeliveryFee)
    }
}
