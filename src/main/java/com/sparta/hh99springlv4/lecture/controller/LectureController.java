package com.sparta.hh99springlv4.lecture.controller;

import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.dto.LectureTeacherResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.service.LectureService;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sparta.hh99springlv4.user.entity.UserRoleEnum.Authority.ADMIN;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {


    private final LectureService lectureService;

    // 강의 등록
    @Secured(ADMIN)
    @PostMapping("/lecture")
    public ResponseEntity<?> createLecture(@RequestBody LectureRequestDto lectureRequestDto) {

      LectureResponseDto lectureResponseDto = lectureService.createLecture(lectureRequestDto);

      return ResponseEntity.ok(lectureResponseDto);

    }

    // 선택한 강의 조회
    @GetMapping("/select/lecture")
    public LectureTeacherResponseDto selectLecture(@RequestBody LectureRequestDto lectureRequestDto) {

        LectureTeacherResponseDto lectureTeacherResponseDto = lectureService.selectLecture(lectureRequestDto);

        return lectureTeacherResponseDto;
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

    //     카테고리별 강의 목록 조회
//    @GetMapping("/find/lecture/{category}")
//    public List<LectureResponseDto> findCategoryLecuture(@PathVariable CategoryEnum category, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if (userDetails != null
//                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
//                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
//            return lectureService.findLecturesByCategory(category);
//        }
//        throw new IllegalArgumentException("관리자가 아닙니다. 선택한 강사가 촬영한 강의 목록 조회를 할 수 없습니다.");
//    }
}
