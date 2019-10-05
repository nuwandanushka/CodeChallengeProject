package com.java.codeChallenge.enums;

public enum ErrorCodeEnum {
    FILE_NOT_FOUND("File is not available in the location"),
    INVALID_SEARCH_TERM("Invalid search term found");

    public String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }
}
