package com.sparta.hh99springlv4.domain.teacher.controller;


import com.sparta.hh99springlv4.domain.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.domain.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.domain.teacher.service.TeacherService;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@Tag(name = "Teacher API", description = "강사와 관련된 API 정보를 담고 있습니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    // 강사 등록
//    @Operation(summary = "강사 등록 기능", description = "강사를 등록할 수 있는 API")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping
    public ResponseEntity<ResponseDto<TeacherResponseDto>> createTeacher(@RequestBody TeacherRequestDto teacherRequestDto) {
        TeacherResponseDto teacherResponseDto = teacherService.createTeacher(teacherRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "강사 등록 기능", teacherResponseDto)
        );
    }
}
