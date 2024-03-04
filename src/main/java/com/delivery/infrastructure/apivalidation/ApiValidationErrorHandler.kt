package com.delivery.infrastructure.apivalidation

import com.delivery.domain.deliveryfeecalculator.OrderDataNotValidException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ApiValidationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): ApiValidationErrorDto {
        val errors = getErrorsFromException(exception)

        return ApiValidationErrorDto(errors, HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderDataNotValidException::class)
    @ResponseBody
    fun handleOrderDataNotValidException(exception: OrderDataNotValidException): ApiValidationErrorDto {
        return ApiValidationErrorDto(exception.errorMessages, HttpStatus.BAD_REQUEST)
    }

    private fun getErrorsFromException(exception: MethodArgumentNotValidException): List<String> {
        return exception.bindingResult
            .allErrors
            .map { it.defaultMessage ?: "" }
    }
}
