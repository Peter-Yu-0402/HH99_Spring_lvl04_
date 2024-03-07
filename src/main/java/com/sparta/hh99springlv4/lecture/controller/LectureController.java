package com.sparta.hh99springlv4.lecture.controller;

import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto.*;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto.*;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.service.LectureService;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {


    private final LectureService lectureService;

    // 강의 등록
    @PostMapping("/lecture")
    public ResponseEntity<?> createLecture(@RequestBody CreateLectureRequestDto lectureRequestDto) {

      CreateLectureResponseDto lectureResponseDto = lectureService.createLecture(lectureRequestDto);

      return ResponseEntity.ok(lectureResponseDto);

    }

    // 선택한 강의 조회
    @GetMapping("/lecture")
    public ReadLectureResponseDto readLecture(@RequestBody ReadLectureRequestDto lectureRequestDto) {

        ReadLectureResponseDto lectureResponseDto = lectureService.readLecture(lectureRequestDto);

        return lectureResponseDto;
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/lecture/category")
    public List<FindLectureResponseDto> findLecturesByCategory(@RequestBody FindLectureRequestDto lectureRequestDto) {

        List<FindLectureResponseDto> responseDtos = lectureService.findLecturesByCategory(lectureRequestDto);
        return responseDtos;
    }



    // 선택한 강의 정보 수정
//    @PutMapping("/lectureinfo/{lectureId}")
//    public LectureResponseDto infoLecture(@PathVariable Long lectureId, @RequestBody LectureRequestDto lectureRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if (userDetails != null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
//            return lectureService.infoLecture(lectureId, lectureRequestDto);
//        }
//        throw new IllegalArgumentException("매니저가 아닙니다. 선택한 정보를 수정 할 수 없습니다.");
//    }
//


    // 선택한 강의 조회 + 댓글 조회

//    @GetMapping("/select/lecture/comment/{lectureId}")
//    public ResponseEntity<LectureCommentDto> getLectureWithComments(@PathVariable Long lectureId) {
//        LectureCommentDto lectureWithCommentsDto = lectureService.findLectureComment(lectureId);
//        return ResponseEntity.ok(lectureWithCommentsDto);
//    }


}
