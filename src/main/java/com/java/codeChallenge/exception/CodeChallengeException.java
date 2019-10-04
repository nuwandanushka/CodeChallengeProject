package com.java.codeChallenge.exception;

import com.java.codeChallenge.enums.ErrorCodeEnum;

public class CodeChallengeException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum;

    public CodeChallengeException(ErrorCodeEnum errorCodeEnum){
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}
