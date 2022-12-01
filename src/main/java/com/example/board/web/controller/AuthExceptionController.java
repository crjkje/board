package com.example.board.web.controller;

import com.example.board.biz.dto.ErrorResponse;
import com.example.board.web.exception.ExistingMemberException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ControllerAdvice
public class AuthExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingMemberException.class)
    @ResponseBody
    public ErrorResponse signUpMemberAlreadyExists(ExistingMemberException e) {

        ErrorResponse response = ErrorResponse.builder()
                .code(String.valueOf(e.getStatusCode()))
                .message("이미 존재하는 Email 주소입니다.")
                .build();
        return response;
    }
}
