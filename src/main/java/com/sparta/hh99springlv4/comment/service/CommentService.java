package com.sparta.hh99springlv4.comment.service;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.user.entity.User;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import com.sparta.hh99springlv4.user.exception.UnauthorizedException;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final LectureRepository lectureRepository;
    private final CommentRepository commentRepository;

    @Transactional
    // 댓글 등록 기능  // + entity ? 선택한 강의를 조회할 때 해당 강의에 등록된 댓글들도 함께 조회할 수 있습니다.
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Lecture lecture;
        if (commentRequestDto.getId() != null) {
            lecture = lectureRepository.findById(commentRequestDto.getId())
                    .orElseThrow(() -> new NotFoundException("Not found lecture id" + commentRequestDto.getId()));
        } else {
            lecture = lectureRepository.findByLectureName(commentRequestDto.getLectureName())
                    .orElseThrow(() -> new NotFoundException("Not found lecture title" + commentRequestDto.getLectureName()));
        }
        User user = userDetails.getUser();

        // 댓글 생성
        Comment comment = new Comment(commentRequestDto);
        comment.setLecture(lecture);
        comment.setUser(user);

        Comment saveComment = commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    // 선택한 강의의 댓글 수정  // + lecture 비활성화
    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

        Lecture lecture;
        if (commentRequestDto.getId() != null) {
            lecture = lectureRepository.findById(commentRequestDto.getId())
                    .orElseThrow(() -> new NotFoundException("Not found lecture id" + commentRequestDto.getId()));
        } else {
            lecture = lectureRepository.findByLectureName(commentRequestDto.getLectureName())
                    .orElseThrow(() -> new NotFoundException("Not found lecture title" + commentRequestDto.getLectureName()));
        }
        User user = userDetails.getUser();

        // 1. 댓글 조회
        Comment comment = commentRepository.findById(commentRequestDto.getId())
                .orElseThrow(() -> new NotFoundException("해당 댓글은 존재하지 않습니다."));

        // 2. 조회한 댓글이 선택한 강의의 댓글인지 확인
        // 서비스상 해당 댓글이 선택한 강의의 댓글인지 확인하는 건 덜 중요할 수도
        if (!lecture.getId().equals(commentRequestDto.getId())) {
            throw new NotFoundException("해당 강의의 댓글이 아닙니다.");
        }

        // 3. 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        // 댓글 수정
        comment.updateContents(commentRequestDto);
        comment = commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    // 선택한 강의의 선택한 댓글 삭제 // + 댓글 등록한 회원만 수정 가능
    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {

        // DB 에서 commentId로 댓글 정보 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        // 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        // 댓글 삭제
        commentRepository.delete(comment);
    }


//    // 선택한 강의 좋아요 등록  // + 과제에 불린값 // 선택 강의 조회 시 좋아요 수 함께 조회
//    public boolean likesLecture(Long userId, Long lectureId) {
////        Lecture lecture = lectureRepository.findByLectureId(lectureId)
////                .orElseThrow(() -> new NotFoundException("해당 강의를 찾을 수 없습니다." + lectureId));
//        return false;
//    }

//        Like like = likeRepository.findByUserIdAndLectureId(userId, lectureId);
//
//        if (like == null) {
//            // 좋아요가 없는 경우 -> 좋아요 등록
//            like = new Like();
//            like.setUser(userId);
//            like.setLecture(lecture);
//            likeRepository.save(like);
//            return true; // 좋아요 등록 성공
//        } else {
//            // 좋아요가 있는 경우 -> 좋아요 취소
//            likeRepository.delete(like);
//            return false; // 좋아요 취소 성공


}
