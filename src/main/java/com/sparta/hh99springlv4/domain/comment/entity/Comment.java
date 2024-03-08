package com.sparta.hh99springlv4.domain.comment.entity;

import com.sparta.hh99springlv4.domain.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.global.entity.Timestamped;
import com.sparta.hh99springlv4.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 댓글내용
    private String contents;

    // 다대일 연관관계
    @ManyToOne(fetch = FetchType.LAZY) // LAZY로 해야 서버 부하가 줄어든다.
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    // 여러 개의 댓글이 하나의 사용자에게만 속함.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 다대일 연관관계 설정 편의 메서드
    public void setUser(User user) {
        this.user = user;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Comment(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }

    // 댓글 수정 메서드
    public void updateContents(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }
}
