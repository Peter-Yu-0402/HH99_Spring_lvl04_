package com.sparta.hh99springlv4.teacher.dto;

import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherResponseDto {
    private String teacherName; //강사이름
    private int teacherCareer; //경력
    private String teacherCompany; //회사
    private String teacherPhone; //전화번호
    private String teacherIntro; //소개


    public TeacherResponseDto (String teacherName, int teacherCareer, String teacherCompany, String teacherIntro) {
        this.teacherName = teacherName;
        this.teacherCareer = teacherCareer;
        this.teacherCompany = teacherCompany;
        this.teacherIntro = teacherIntro;
    }

    public TeacherResponseDto(Teacher teacher) {
        this.teacherName = teacher.getTeacherName();
        this.teacherCareer = teacher.getTeacherCareer();
        this.teacherCompany = teacher.getTeacherCompany();
        this.teacherPhone = teacher.getTeacherPhone();
        this.teacherIntro = teacher.getTeacherIntro();
    }

//
//    public TeacherResponseDto (Lecture lecture) {
//        this.teacherName =  lecture.getTeacher().getTeacherName();
//        this.teacherCareer = lecture.getTeacher().getTeacherCareer();
//        this.teacherCompany =lecture.getTeacher().getTeacherCompany();
//        this.teacherPhone = lecture.getTeacher().getTeacherPhone();
//        this.teacherIntro = lecture.getTeacher().getTeacherIntro();
//
//    }
}


