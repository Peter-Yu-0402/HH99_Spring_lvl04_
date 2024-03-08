package com.sparta.hh99springlv4.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String userEmail;
    private String userPassword;
}