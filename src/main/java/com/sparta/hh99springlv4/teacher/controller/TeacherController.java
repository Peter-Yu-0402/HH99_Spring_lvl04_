package com.sparta.hh99springlv4.teacher.controller;


import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.service.TeacherService;
import com.sparta.hh99springlv4.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {

    private final TeacherService teacherService;

    // 강사 등록
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/teacher")
    public ResponseEntity<?> createTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {

        TeacherResponseDto teacherResponseDto = teacherService.createTeacher(teacherRequestDto);

        return ResponseEntity.ok(teacherResponseDto);
    }

    // @Secured("ROLE_ADMIN")
    // @Secured(UserRoleEnum.ADMIN)

    // 선택한 강사 정보 수정
    @PutMapping("/teacherInfo/{teacherId}")
    public TeacherResponseDto infoTeacher(@PathVariable Long teacherId, @RequestBody TeacherRequestDto teacherRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return teacherService.infoTeacher(teacherId, teacherRequestDto);
        }
        throw new IllegalArgumentException("매니저가 아닙니다. 선택한 정보를 수정 할 수 없습니다.");
    }

    // 선택한 강사 조회
    @GetMapping("/teacher/{teacherId}")
    public TeacherResponseDto updateTeacher(@PathVariable Long teacherId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return teacherService.updateTeacher(teacherId);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 선택한 강사를 조회 할 수 없습니다.");
    }

    // 선택한 강사가 촬영한 강의 목록 조회
    @GetMapping("/lecture/{teacherId}")
    public List<LectureResponseDto> findTeacherLecutre(@PathVariable Long teacherId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null
                && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return teacherService.findTeacherLecture(teacherId);
        }
        throw new IllegalArgumentException("관리자가 아닙니다. 선택한 강사가 촬영한 강의 목록 조회를 할 수 없습니다.");
    }

}
