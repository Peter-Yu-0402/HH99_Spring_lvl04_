package com.sparta.hh99springlv4.teacher.service;


import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;


    // 강사 등록 기능
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) {

        // RequestDto -> Entity
        Teacher teacher = new Teacher(teacherRequestDto);

        // DB에 저장
        Teacher saveTeacher = teacherRepository.save(teacher);

        // Entity -> ResponseDto
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto(saveTeacher);

        return teacherResponseDto;
    }

    // 선택한 강사 정보 수정
    public TeacherResponseDto infoTeacher(Long teacherId, TeacherRequestDto teacherRequestDto) {

        // 선택한 강사 정보 조회
        Teacher teacher = findTeacher(teacherId);

        // 강사 정보 수정
        teacher.setTeacherCareer(teacherRequestDto.getTeacherCareer());
        teacher.setTeacherCompany(teacherRequestDto.getTeacherCompany());
        teacher.setTeacherPhone(teacherRequestDto.getTeacherPhone());
        teacher.setTeacherIntro(teacherRequestDto.getTeacherIntro());

        // 변경된 강사 정보를 DB에 저장
        teacher = teacherRepository.save(teacher);

        return new TeacherResponseDto(teacher);
    }

    // 선택한 강사 조회
    public TeacherResponseDto updateTeacher(Long teacherId) {

        // 선택한 강사가 teacher DB에 존재하는지 확인
        Teacher teacher = findTeacher(teacherId);

        return new TeacherResponseDto(teacher);
    }


    // 선택한 강사가 촬영한 강의 목록 조회
    public List<LectureResponseDto> findTeacherLecture(Long teacherId) {

//        + teacher entity @one to many 메소드 / + DB 연결하기
        // 강사 조회
        Teacher teacher = findTeacher(teacherId);

        // 강사의 강의 목록 가져오기
        List<Lecture> teacherLectures = teacher.getLectureList();

        // 강의 목록을 등록일 기준으로 내림차순 정렬
//        Collections.sort(teacherLectures, Comparator.comparing(Lecture::getRegistrationDate).reversed());

        // 조회된 강의 목록을 DTO 형식으로 변환하여 반환합니다.
//        return teacherLectures.stream()
//                .map(LectureResponseDto::new) // 각 요소에 대해 객체 생성하는것. lecture 연결 후 생성자 고쳐보기
//                .collect(Collectors.toList());
//
        return null;
    }

    // 강사 조회 메서드
    private Teacher findTeacher(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강사는 존재하지 않습니다."));
    }

}
