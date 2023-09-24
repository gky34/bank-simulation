package com.review.exception;

import com.review.model.Account;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message)
    {
        super(message);
    }
}
