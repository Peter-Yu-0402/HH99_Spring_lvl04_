//package com.sparta.hh99springlv4.global.handler.exception;
//
//import lombok.Getter;
//
//@Getter
//public enum ErrorCode {
//
//    // 로그인
//    NOT_FOUND_ADMIN_ID("찾을 수 없는 관리자입니다."),
//    NOT_FOUND_ADMIN_PW("찾을 수 없는 관리자 비밀번호입니다."),
//    // 공통
//    TOKEN_NOT_VALID("토큰이 일치하지 않습니다."),
//    UNAUTHORIZED_ADMIN("권한 없는 관리자입니다."),
//    BAD_REQUEST_ADMIN_AUTHENTICATION("인증되지 않은 사용자입니다."),
//
//    // 강사
//    NOT_FOUND_TEACHER_ID("찾을 수 없는 강사 정보입니다."),
//    // 강의
//    NOT_FOUND_CATEGORY_ID("찾을 수 없는 강의 종류입니다."), // 카테고리
//    NOT_FOUND_LECTURE_ID("찾을 수 없는 강의 정보입니다."), // 강의 정보조회시
//
//
//    //회원가입
//    ALREADY_REGISTERED_EMAIL("이미 가입된 이메일입니다."),
//    ;
//
//    private final String message;
//
//    ErrorCode(String message) {
//        this.message = message;
//    }
//}
