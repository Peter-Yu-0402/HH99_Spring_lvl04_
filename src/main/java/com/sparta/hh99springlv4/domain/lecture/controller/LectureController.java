package com.sparta.hh99springlv4.domain.lecture.controller;

import com.sparta.hh99springlv4.domain.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.service.LectureService;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {


    private final LectureService lectureService;

    // 강의 등록
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/lecture")
    public ResponseEntity<LectureResponseDto.CreateLectureResponseDto> createLecture(@RequestBody LectureRequestDto.CreateLectureRequestDto lectureRequestDto) {

      LectureResponseDto.CreateLectureResponseDto lectureResponseDto = lectureService.createLecture(lectureRequestDto);

      return ResponseEntity.ok(lectureResponseDto);

    }

    // 선택한 강의 조회
    @GetMapping("/lecture/lectureBy/{lectureId}")
    public ResponseEntity<LectureResponseDto.ReadLectureResponseDto> readLecture(@PathVariable Long lectureId) {

        LectureResponseDto.ReadLectureResponseDto lectureResponseDto = lectureService.readLecture(lectureId);

        return ResponseEntity.ok(lectureResponseDto);
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/lecture/sorted")
    public ResponseEntity<List<LectureResponseDto.FindLectureResponseDto>> findLecturesByCategory(@RequestParam(value = "categoryBy", required = true) String categoryBy,
                                                                                                  @RequestParam(value = "lectureBy", required = false) String lectureBy,
                                                                                                  @RequestParam(value = "orderBy", required = false) String orderBy) {

        List<LectureResponseDto.FindLectureResponseDto> responseDto = lectureService.findLecturesByCategory(categoryBy, orderBy, lectureBy);
        return ResponseEntity.ok(responseDto);
    }
}
