//package com.sparta.hh99springlv4.global.handler;
//
//import com.sparta.hh99springlv4.global.dto.ResponseDto;
//import com.sparta.hh99springlv4.global.handler.exception.CustomApiException;
//import com.sparta.hh99springlv4.global.handler.exception.CustomValidationException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class CustomExceptionHandler {
//
//    @ExceptionHandler(CustomApiException.class)
//    public ResponseEntity<?> handleCustomApiException(CustomApiException e) {
//        return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getMessage(), null));
//    }
//
//    @ExceptionHandler(CustomValidationException.class)
//    public ResponseEntity<?> handleValidationApiException(CustomValidationException e) {
//        return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getMessage(), e.getErrorMap()));
//    }
//}
//
