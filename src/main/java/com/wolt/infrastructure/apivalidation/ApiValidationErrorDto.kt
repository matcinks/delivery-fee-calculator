package com.wolt.infrastructure.apivalidation

import org.springframework.http.HttpStatus

data class ApiValidationErrorDto(
    val messages: List<String>,
    val status: HttpStatus
)