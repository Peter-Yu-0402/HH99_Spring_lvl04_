package com.sparta.hh99springlv4.domain.comment.service;

import com.sparta.hh99springlv4.domain.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.domain.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import com.sparta.hh99springlv4.domain.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.global.handler.exception.NotFoundException;
import com.sparta.hh99springlv4.global.handler.exception.UnauthorizedException;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final LectureRepository lectureRepository;
    private final CommentRepository commentRepository;

    @Transactional
    // 댓글 등록 기능
    public CommentResponseDto createComment(Long lectureId,
                                            CommentRequestDto commentRequestDto,
                                            UserDetailsImpl userDetails) {
        // lectureId로 특정하여 Lecture 객체
        Lecture lecture = lectureRepository.findById(lectureId)
                            .orElseThrow(() -> new NotFoundException("Not found lecture id" + lectureId));
        // 현재 로그인된 유저를 나타내는 User 객체
        User user = userDetails.getUser();

        // RequestDto -> Entity
        // DTO에 담긴 내용으로 댓글 객체 생성
        Comment comment = new Comment(commentRequestDto);

        // 다대일 관계의 Comment Entity의 엔티티 연관관계 설정
        comment.setLecture(lecture);
        comment.setUser(user);

        // Repository 저장
        Comment saveComment = commentRepository.save(comment);
        // Entity -> ResponseDto
        return  new CommentResponseDto(saveComment);
    }

    // 선택한 강의의 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long lectureId,
                                            Long commentId,
                                            CommentRequestDto commentRequestDto,
                                            UserDetailsImpl userDetails) {

        // lectureId로 특정하여 Lecture 존재 여부 확인
        Lecture lecture = lectureRepository.findById(lectureId)
                    .orElseThrow(() -> new NotFoundException("Not found lecture id" + lectureId));

        // commentId로 특정된 게시글에 대한 댓글 객체
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글은 존재하지 않습니다."));

        // commentId를 작성한 userId 특정
        Long commentUserId = comment.getUser().getId();

        // 현재 로그인 유저의 userId 특정
        Long userId = userDetails.getUser().getId();

        // 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치 여부 확인
        if (!commentUserId.equals(userId)) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        // 댓글 수정 및 저장
        comment.updateContents(commentRequestDto);
        comment = commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    // 선택한 강의의 선택한 댓글 삭제 // + 댓글 등록한 회원만 수정 가능
    @Transactional
    public void deleteComment(Long lectureId, Long commentId, UserDetailsImpl userDetails) {
        // lectureId로 특정하여 Lecture 존재 여부 확인
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new NotFoundException("Not found lecture id : " + lectureId));

        // DB에서 commentId로 댓글 정보 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        // commentId를 작성한 userId 특정
        Long commentUserId = comment.getUser().getId();

        // 현재 로그인 유저의 userId 특정
        Long userId = userDetails.getUser().getId();

        // 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
        if (!commentUserId.equals(userId)) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        // 댓글 삭제
        commentRepository.delete(comment);
    }
}
