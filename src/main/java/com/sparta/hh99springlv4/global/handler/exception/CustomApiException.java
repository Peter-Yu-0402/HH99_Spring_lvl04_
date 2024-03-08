package com.sparta.hh99springlv4.global.handler.exception;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    public CustomApiException(String message) {
        super(message);
    }
}