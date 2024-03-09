package com.sparta.hh99springlv4.domain.likes.controller;

import com.sparta.hh99springlv4.domain.likes.service.LikesService;
import com.sparta.hh99springlv4.domain.likes.dto.LikesResponseDto;
import com.sparta.hh99springlv4.global.dto.ResponseDto;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@Tag(name = "Likes API", description = "좋아요와 관련된 API 정보를 담고 있습니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lecture")
public class LikesController {
    private final LikesService likesService;

    // 강의 좋아요 등록
//    @Operation(summary = "선택한 강의 좋아요 기능", description = "선택한 강의를 좋아요할 수 있는 API")
    @PostMapping("/{lectureId}/likes")
    public ResponseEntity<ResponseDto<LikesResponseDto>> likeLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        LikesResponseDto likesResponseDto = likesService.likeLecture(lectureId, userDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "선택강의 좋아요 기능", likesResponseDto)
        );
    }
}
