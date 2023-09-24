package com.review.exception;

public class BalanceNotSuffincientException extends RuntimeException {
    public BalanceNotSuffincientException(String message) {
        super(message);
    }
}
