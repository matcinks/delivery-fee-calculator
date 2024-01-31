package com.wolt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object DeliveryFeeCalculatorApp {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(DeliveryFeeCalculatorApp::class.java, *args)
    }
}