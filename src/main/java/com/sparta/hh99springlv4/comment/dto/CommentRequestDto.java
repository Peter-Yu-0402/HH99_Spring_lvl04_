package com.sparta.hh99springlv4.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long id = null; // 강의 id
    private String lectureName; // 강의 제목
    private String contents; //댓글내용
    private long likes = 0; //좋아요


}
