package com.example.board.web.exception;

import lombok.Getter;

@Getter
public class InvalidRequest extends CustomException {

    private static final String MESSAGE = "잘못된 요청입니다.";
    private String fieldName;
    private String message;

    @Override
    public int getStatusCode() {
        return 400;
    }

    public InvalidRequest(Throwable cause) {
        super(MESSAGE, cause);
    }

    public InvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }


}

