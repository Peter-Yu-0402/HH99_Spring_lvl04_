package com.sparta.hh99springlv4.lecture.dto;

import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureTeacherResponseDto {
    private String lectureName; // 강의명
    private Long lecturePrice; // 가격
    private String lectureIntro; // 소개
    private CategoryEnum lectureCategory; // 카테고리
    private LocalDate lectureRegistrationDate;
    private String teacherName; // 강사이름
    private int teacherCareer; // 강사 경력
    private String teacherCompany; // 강사 회사
    private String teacherIntro; // 강사 소개

    public LectureTeacherResponseDto(Lecture lecture) {
        this.lectureName = lecture.getLectureName();
        this.lecturePrice = lecture.getLecturePrice();
        this.lectureIntro = lecture.getLectureIntro();
        this.lectureCategory = lecture.getLectureCategory();
        this.lectureRegistrationDate = lecture.getLectureRegistrationDate();

        Teacher teacher = lecture.getTeacher();
        this.teacherName = teacher.getTeacherName();
        this.teacherCareer = teacher.getTeacherCareer();
        this.teacherCompany = teacher.getTeacherCompany();
        this.teacherIntro = teacher.getTeacherIntro();
    }

}
