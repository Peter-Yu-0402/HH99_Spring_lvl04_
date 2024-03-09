package com.sparta.hh99springlv4.domain.user.controller;


import com.sparta.hh99springlv4.domain.user.dto.SignupRequestDto;
import com.sparta.hh99springlv4.domain.user.dto.SignupResponseDto;
import com.sparta.hh99springlv4.domain.user.service.UserService;
import com.sparta.hh99springlv4.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User API", description = "회원과 관련된 API 정보를 담고 있습니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    // 회원 가입
    @Operation(summary = "회원 등록 기능", description = "회원을 등록할 수 있는 API")
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<SignupResponseDto>> signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(
                    new ResponseDto<>(false, "회원 가입 실패", null));
        }
        SignupResponseDto responseDto = userService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<>(true, "회원 가입", responseDto)
        );
    }
}