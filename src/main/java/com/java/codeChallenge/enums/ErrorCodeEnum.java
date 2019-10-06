package com.java.codeChallenge.enums;

public enum ErrorCodeEnum {
    FILE_NOT_FOUND("File is not available in the location"),
    INVALID_SEARCH_TERM("Invalid search term found"),
    EMPTY_VALUE_MAP("Display results value map is empty"),
    PARSE_EXCEPTION("Unable transfrom reader object to JSON array");

    public String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }
}
