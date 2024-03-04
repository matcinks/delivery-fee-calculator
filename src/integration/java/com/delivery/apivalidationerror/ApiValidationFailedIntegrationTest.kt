package com.delivery.apivalidationerror

import com.fasterxml.jackson.module.kotlin.readValue
import com.delivery.BaseIntegrationTest
import com.delivery.infrastructure.apivalidation.ApiValidationErrorDto
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.assertj.core.api.Assertions.assertThat

class ApiValidationFailedIntegrationTest : BaseIntegrationTest() {

    @Test
    fun should_return_400_bad_request_and_validation_message_when_request_is_empty() {
        // given
        // when
        val perform: ResultActions = mockMvc.perform(
            post("/deliveryFee")
                .content(
                    """
                        {}
                    """.trimIndent()
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
        // then
        val mvcResult: MvcResult = perform.andExpect(status().isBadRequest).andReturn()
        val json: String = mvcResult.response.contentAsString
        val result: ApiValidationErrorDto = objectMapper.readValue(json)
        assertThat(result.messages).containsExactlyInAnyOrder(
            "cart value must be not null",
            "delivery distance must be not null",
            "number of items must be not null",
            "order time must be not null"
        )
    }

    @Test
    fun should_return_400_bad_request_and_validation_message_when_values_in_request_are_not_positive_numbers() {
        // given
        // when
        val perform: ResultActions = mockMvc.perform(
            post("/deliveryFee")
                .content(
                    """
                        {
                        "cart_value": -20,
                        "delivery_distance": 0,
                        "number_of_items": -1,
                        "time": "2024-01-15T13:00:00Z"
                        }
                    """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON)
        )
        // then
        val mvcResult: MvcResult = perform.andExpect(status().isBadRequest).andReturn()
        val json: String = mvcResult.response.contentAsString
        val apiValidationErrorDto: ApiValidationErrorDto =
            objectMapper.readValue(json)
        val validationMessages = apiValidationErrorDto.messages
        val expectedValidationMessages = listOf(
            "cart value must be not null",
            "delivery distance must be not null",
            "number of items must be not null"
        )
        assertThat(validationMessages).containsExactlyInAnyOrderElementsOf(expectedValidationMessages)
    }
}
