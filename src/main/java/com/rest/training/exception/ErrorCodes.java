package com.rest.training.exception;

public enum ErrorCodes {
    NOT_FOUND("4004", "Unable to find resource"),
    CATEGORY_NAME_EMPTY("40005", "Category should not be empty"),
    PET_NAME_EMPTY("40006", "Pet name should not be empty"),
    INVALID_PAGE_PARAM("5000", "Invalid Page parameter");

    private final String code;
    private final String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCodes fromCode(String code) {
        for(ErrorCodes err : ErrorCodes.values()) {
            if(err.getCode().equals(code)) {
                return err;
            }
        }
        throw new IllegalArgumentException("Code not found "+code);
    }
}
