package com.wolt.apivalidationerror

import com.wolt.BaseIntegrationTest
import com.wolt.infrastructure.apivalidation.ApiValidationErrorDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ApiValidationFailedIntegrationTest : BaseIntegrationTest() {
    @Test
    @Throws(Exception::class)
    fun should_return_400_bad_request_and_validation_message_when_request_is_empty() {
        // given
        // when
        val perform = mockMvc!!.perform(MockMvcRequestBuilders.post("/deliveryFee")
                .content("""
                        {}
                        
                        """.trimIndent().trim { it <= ' ' }
                ).contentType(MediaType.APPLICATION_JSON)
        )
        // then
        val mvcResult = perform.andExpect(MockMvcResultMatchers.status().isBadRequest).andReturn()
        val json = mvcResult.response.contentAsString
        val result = objectMapper!!.readValue(json, ApiValidationErrorDto::class.java)
        assertThat(result.messages()).containsExactlyInAnyOrder(
                "cart value must be not null",
                "delivery distance must be not null",
                "number of items must be not null",
                "order time must be not null"
        )
    }

    @Test
    @Throws(Exception::class)
    fun should_return_400_bad_request_and_validation_message_when_values_in_request_are_not_positive_numbers() {
        // given
        // when
        val perform = mockMvc!!.perform(MockMvcRequestBuilders.post("/deliveryFee")
                .content("""
                        {
                            "cart_value": -20,
                            "delivery_distance": 0,
                            "number_of_items": -1,
                            "time": "2024-01-15T13:00:00Z"
                        }
                        
                        """.trimIndent().trim { it <= ' ' }
                ).contentType(MediaType.APPLICATION_JSON)
        )
        // then
        val mvcResult = perform.andExpect(MockMvcResultMatchers.status().isBadRequest).andReturn()
        val json = mvcResult.response.contentAsString
        val result = objectMapper!!.readValue(json, ApiValidationErrorDto::class.java)
        assertThat(result.messages()).containsExactlyInAnyOrder(
                "cart value must be positive number",
                "number of items must be positive number",
                "delivery distance must be positive number"
        )
    }
}