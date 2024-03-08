package com.sparta.hh99springlv4.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {

    private boolean status;
    private String message;
    private T data;
}
