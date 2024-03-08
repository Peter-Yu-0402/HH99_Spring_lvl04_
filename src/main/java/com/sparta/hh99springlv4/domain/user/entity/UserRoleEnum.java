package com.sparta.hh99springlv4.domain.user.entity;

public enum UserRoleEnum {
    ADMIN(Authority.ADMIN),
    USER(Authority.USER);

    private final String authority;

    UserRoleEnum(String authority) { // 생성자
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority { // 메서드
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}



