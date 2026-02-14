package com.example.demonstration.shared;

import lombok.Getter;

@Getter
public class CustomResponseException extends RuntimeException {

    private final int code;

    public CustomResponseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public static CustomResponseException ResourceNotFound(String message) {
        return new CustomResponseException(404, message);
    }
}
