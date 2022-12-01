package com.example.board.web.exception;

public class MemberNotFound extends CustomException {
    private static final String MESSAGE = "존재하지 않는 회원입니다.";
    public MemberNotFound() {
        super(MESSAGE);
    }

    public MemberNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
