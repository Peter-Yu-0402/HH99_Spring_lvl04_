package com.sparta.hh99springlv4.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.user.entity.Timestamped;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String contents; // 댓글내용
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt; // 등록일
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt; // 수정일

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
