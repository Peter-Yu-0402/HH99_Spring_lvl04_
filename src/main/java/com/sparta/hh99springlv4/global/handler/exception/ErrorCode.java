package com.sparta.hh99springlv4.global.handler.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND_BOOK_ID("찾을 수 없는 도서 번호입니다."),
    NOT_FOUND_USER_ID("찾을 수 없는 회원 번호입니다."),
    NOT_FOUND_LOAN_INFORMATION("찾을 수 없는 대출 정보입니다."),
    ALREADY_REGISTERED_PHONE("이미 가입된 휴대폰 번호입니다."),
    ALREADY_REGISTERED_IDENTIFICATION("이미 가입된 주민번호입니다."),
    ALREADY_BOOK_LOAN("이미 대출 상태의 도서입니다."),
    PENALTY_PREVENTS_LOAN("패널티로 인해 도서를 대출 받을 수 없습니다."),
    UNRETURNED_BOOK_EXISTS("반납하지 않은 도서가 존재합니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
