package com.sparta.hh99springlv4.comment.dto;

import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.user.entity.Timestamped;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CommentResponseDto extends Timestamped {
    private String contents; // 댓글내용
    private Long likes; // 좋아요
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt; // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt; // 수정일

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.likes = comment.getLikes();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
