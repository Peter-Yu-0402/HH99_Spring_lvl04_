package com.sparta.hh99springlv4.comment.controller;

import com.sparta.hh99springlv4.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.comment.service.CommentService;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 선택한 강의의 댓글 등록
    @PostMapping("/lectures/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto, userDetails);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    // 선택한 강의의 댓글 수정
    @PutMapping("/lectures/comments")
    // 코멘트 아이디만 있어도 댓글 수정이 가능하니, 렉쳐 아이디는 불필요할 수 있다. 매개변수가 많은 건 권장하지 않음
    // 아예 리퀘스트디티오에 담아서 한꺼번에 담는 방법도 있다.
    public ResponseEntity<?> updateComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentRequestDto, userDetails);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    // 선택한 강의의 선택한 댓글 삭제
    @DeleteMapping("/lectures/comments/{commentId}")
    public CommentResponseDto deleteComment(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(lectureId, userDetails);
    }

    // 선택한 강의 좋아요 등록
//    @PostMapping("/{lectureId}/likes")
//    public CommentResponseDto likesLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return commentService.likesLecture(lectureId);
//    }
}
