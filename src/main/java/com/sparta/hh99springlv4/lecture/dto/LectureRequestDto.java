package com.sparta.hh99springlv4.lecture.dto;

import com.sparta.hh99springlv4.teacher.entity.Teacher;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureRequestDto {
    private String lectureName; //강의명
    private Long lecturePrice; //가격
    private String lectureIntro; //소개
    private String lectureCategory; //카테고리
    private LocalDate lectureRegistrationDate; //등록일
    private String teacherName; //강사이름


}
