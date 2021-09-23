package com.rest.training.exception;

public class CategoryNotFoundException extends Exception {
    private final ErrorCodes ERR_CODE = ErrorCodes.NOT_FOUND;

    public ErrorCodes getErrorCode() {
        return ERR_CODE;
    }
}
