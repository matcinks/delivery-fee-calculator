package com.wolt.infrastructure.apivalidation

import org.springframework.http.HttpStatus
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@ControllerAdvice
class ApiValidationErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): ApiValidationErrorDto {
        val errors = getErrorsFromException(exception)
        return ApiValidationErrorDto(errors, HttpStatus.BAD_REQUEST)
    }

    private fun getErrorsFromException(exception: MethodArgumentNotValidException): List<String> {
        return exception.bindingResult
                .allErrors
                .stream()
                .map { obj: ObjectError -> obj.defaultMessage }
                .collect(Collectors.toList())
    }
}