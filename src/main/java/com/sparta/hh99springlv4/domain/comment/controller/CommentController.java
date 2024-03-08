package com.sparta.hh99springlv4.domain.comment.controller;

import com.sparta.hh99springlv4.domain.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.domain.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.domain.comment.service.CommentService;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
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
    @PostMapping("/lecture/{lectureId}/comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long lectureId,
                                                            @RequestBody CommentRequestDto commentRequestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto = commentService.createComment(lectureId, commentRequestDto, userDetails);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    // 선택한 강의의 댓글 수정
    @PutMapping("/lecture/{lectureId}/comment/{commentId}")
    // 코멘트 아이디만 있어도 댓글 수정이 가능하니, 렉쳐 아이디는 불필요할 수 있다. 매개변수가 많은 건 권장하지 않음
    // 아예 리퀘스트디티오에 담아서 한꺼번에 담는 방법도 있다.
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long lectureId,
                                           @PathVariable Long commentId,
                                           @RequestBody CommentRequestDto commentRequestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto = commentService.updateComment(lectureId, commentId, commentRequestDto, userDetails);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    // 선택한 강의의 선택한 댓글 삭제
    @DeleteMapping("/lecture/{lectureId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long lectureId,
                                        @PathVariable Long commentId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(lectureId, commentId, userDetails);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
