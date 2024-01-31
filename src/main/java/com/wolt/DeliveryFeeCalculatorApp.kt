package com.wolt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class DeliveryFeeCalculatorApp

fun main(args: Array<String>) {
    SpringApplication.run(DeliveryFeeCalculatorApp::class.java, *args)
}