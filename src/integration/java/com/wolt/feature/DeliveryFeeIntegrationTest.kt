package com.wolt.feature;

import com.wolt.BaseIntegrationTest;
import com.wolt.domain.deliveryfeecalculator.dto.DeliveryFeeCalculatorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryFeeIntegrationTest extends BaseIntegrationTest {

    @Test
    void should_calculate_delivery_fee() throws Exception {
        // given
        // when
        ResultActions perform = mockMvc.perform(post("/deliveryFee")
                .content("""
                        {
                        "cart_value": 790,
                        "delivery_distance": 2235,
                        "number_of_items": 4,
                        "time": "2024-01-15T13:00:00Z"
                        }
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        DeliveryFeeCalculatorResponseDto deliveryFeeCalculatorResponseDto = objectMapper.readValue(json, DeliveryFeeCalculatorResponseDto.class);
        BigInteger expectedDeliveryFee = BigInteger.valueOf(710);
        assertThat(deliveryFeeCalculatorResponseDto.deliveryFee()).isEqualTo(expectedDeliveryFee);
    }
}
