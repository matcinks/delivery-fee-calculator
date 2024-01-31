package com.wolt.domain.deliveryfeecalculator

class OrderDataNotValidException internal constructor(val errorMessages: List<String>) : RuntimeException(errorMessages.joinToString(", "))