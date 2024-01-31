package com.wolt.apivalidationerror;

import com.wolt.BaseIntegrationTest;
import com.wolt.infrastructure.apivalidation.ApiValidationErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Test
    void should_return_400_bad_request_and_validation_message_when_request_is_empty() throws Exception {
        // given
        // when
        ResultActions perform = mockMvc.perform(post("/deliveryFee")
                .content("""
                        {}
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.messages()).containsExactlyInAnyOrder(
                "cart value must be not null",
                "delivery distance must be not null",
                "number of items must be not null",
                "order time must be not null"
        );
    }

    @Test
    void should_return_400_bad_request_and_validation_message_when_values_in_request_are_not_positive_numbers() throws Exception {
        // given
        // when
        ResultActions perform = mockMvc.perform(post("/deliveryFee")
                .content("""
                        {
                            "cart_value": -20,
                            "delivery_distance": 0,
                            "number_of_items": -1,
                            "time": "2024-01-15T13:00:00Z"
                        }
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        ApiValidationErrorDto result = objectMapper.readValue(json, ApiValidationErrorDto.class);
        assertThat(result.messages()).containsExactlyInAnyOrder(
                "cart value must be positive number",
                "number of items must be positive number",
                "delivery distance must be positive number"
        );
    }
}
