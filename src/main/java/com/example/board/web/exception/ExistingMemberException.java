package com.example.board.web.exception;

public class ExistingMemberException extends CustomException{
    private static final String MESSAGE = "이미 존재하는 Email 주소입니다.";
    private String fieldName;
    private String message;

    @Override
    public int getStatusCode() {
        return 404;
    }

    public ExistingMemberException() {
        super(MESSAGE);
    }
    public ExistingMemberException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public ExistingMemberException(String fieldName, String message) {
        super(MESSAGE);addValidation(fieldName, message);

    }
}
