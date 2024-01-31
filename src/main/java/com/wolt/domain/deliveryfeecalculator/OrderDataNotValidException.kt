package com.wolt.domain.deliveryfeecalculator;

public class OrderDataNotValidException extends RuntimeException {

    OrderDataNotValidException(String message) {
        super(message);
    }
}
