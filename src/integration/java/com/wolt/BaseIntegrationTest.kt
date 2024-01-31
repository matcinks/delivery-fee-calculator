package com.wolt

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(classes = [DeliveryFeeCalculatorApp::class])
@ActiveProfiles("integration")
@AutoConfigureMockMvc
open class BaseIntegrationTest {
    @Autowired
    var mockMvc: MockMvc? = null

    @Autowired
    var objectMapper: ObjectMapper? = null
}