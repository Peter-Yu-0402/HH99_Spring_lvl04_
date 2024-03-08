package com.sparta.hh99springlv4.likes.controller;

import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.service.LectureService;
import com.sparta.hh99springlv4.likes.dto.LikesResponseDto;
import com.sparta.hh99springlv4.likes.service.LikesService;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikesController {
    private final LikesService likesService;

    // 강의 등록
    @PostMapping("/lecture/{lectureId}/likes")
    public ResponseEntity<LikesResponseDto> likeLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        LikesResponseDto likesResponseDto = likesService.likeLecture(lectureId, userDetails);

        return ResponseEntity.ok(likesResponseDto);

    }
//    @DeleteMapping("/lecture/{lectureId}/likes")
//    public void dislikeLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        likesService.dislikeLecture(lectureId, userDetails);
//    }
}
