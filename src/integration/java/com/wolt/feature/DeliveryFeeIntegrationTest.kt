package com.wolt.feature

import com.wolt.BaseIntegrationTest
import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigInteger

class DeliveryFeeIntegrationTest : BaseIntegrationTest() {
    @Test
    @Throws(Exception::class)
    fun should_calculate_delivery_fee() {
        // given
        // when
        val perform = mockMvc!!.perform(MockMvcRequestBuilders.post("/deliveryFee")
                .content("""
                        {
                        "cart_value": 790,
                        "delivery_distance": 2235,
                        "number_of_items": 4,
                        "time": "2024-01-15T13:00:00Z"
                        }
                        
                        """.trimIndent().trim { it <= ' ' }
                ).contentType(MediaType.APPLICATION_JSON)
        )
        // then
        val mvcResult = perform.andExpect(MockMvcResultMatchers.status().isOk).andReturn()
        val json = mvcResult.response.contentAsString
        val deliveryFeeCalculatorResponseDto = objectMapper!!.readValue(json, DeliveryFeeCalculatorResponseDto::class.java)
        val expectedDeliveryFee = BigInteger.valueOf(710)
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee)
    }
}