package com.sparta.hh99springlv4.domain.lecture.controller;

import com.sparta.hh99springlv4.domain.lecture.dto.LectureRequestDto.*;
import com.sparta.hh99springlv4.domain.lecture.service.LectureService;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.*;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Lecture API", description = "강의와 관련된 API 정보를 담고 있습니다.")

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lecture")
public class LectureController {


    private final LectureService lectureService;

    // 강의 등록
//    @Operation(summary = "강의 등록 기능", description = "강의를 등록할 수 있는 API")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/lecture")
    public ResponseEntity<ResponseDto<CreateLectureResponseDto>> createLecture(@RequestBody CreateLectureRequestDto lectureRequestDto) {
      CreateLectureResponseDto lectureResponseDto = lectureService.createLecture(lectureRequestDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(
              new ResponseDto<>(true, "강의 등록", lectureResponseDto)
      );

    }

    // 선택한 강의 조회
//    @Operation(summary = "강의 조회 기능", description = "강의를 조회할 수 있는 API")
    @GetMapping("/{lectureId}")
    public ResponseEntity<ResponseDto<ReadLectureResponseDto>> readLecture(@PathVariable Long lectureId) {
        ReadLectureResponseDto lectureResponseDto = lectureService.readLecture(lectureId);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "강의 조회", lectureResponseDto)
        );
    }

    // 카테고리별 강의 목록 조회
//    @Operation(summary = "강의 카테고리 조회 기능", description = "강의 카테고리를 조회할 수 있는 API")
    @GetMapping("/sorted")
    public ResponseEntity<ResponseDto<List<FindLectureResponseDto>>> findLecturesByCategory(@RequestParam(value = "categoryBy", required = true) String categoryBy,
                                                                                                  @RequestParam(value = "lectureBy", required = false) String lectureBy,
                                                                                                  @RequestParam(value = "orderBy", required = false) String orderBy) {

        List<FindLectureResponseDto> responseDto = lectureService.findLecturesByCategory(categoryBy, orderBy, lectureBy);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "강의 카테고리 조회", responseDto)
        );
    }
}
