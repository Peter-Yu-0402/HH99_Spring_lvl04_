package com.sparta.hh99springlv4.lecture.dto;

import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class LectureResponseDto {
    private String lectureName; //강의명
    private Long lecturePrice; //가격
    private String lectureIntro; //소개
    private CategoryEnum lectureCategory; // 카테고리
    private LocalDate lectureRegistrationDate;
    private String teacherName; //강사이름

    public LectureResponseDto(Lecture lecture) {
        this.lectureName = lecture.getLectureName();
        this.lecturePrice = lecture.getLecturePrice();
        this.lectureIntro = lecture.getLectureIntro();
        this.lectureCategory = lecture.getLectureCategory();
        this.lectureRegistrationDate = lecture.getLectureRegistrationDate();
        this.teacherName = lecture.getTeacher().getTeacherName();
    }
}
