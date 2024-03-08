package com.sparta.hh99springlv4.domain.likes.controller;

import com.sparta.hh99springlv4.domain.likes.service.LikesService;
import com.sparta.hh99springlv4.domain.likes.dto.LikesResponseDto;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
