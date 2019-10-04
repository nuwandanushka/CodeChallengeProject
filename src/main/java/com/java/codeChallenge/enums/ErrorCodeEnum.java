package com.java.codeChallenge.enums;

public enum ErrorCodeEnum {
    FILE_NOT_FOUND("File is not available in the location");

    public String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }
}
