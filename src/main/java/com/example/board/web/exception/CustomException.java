package com.example.board.web.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class CustomException  extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
