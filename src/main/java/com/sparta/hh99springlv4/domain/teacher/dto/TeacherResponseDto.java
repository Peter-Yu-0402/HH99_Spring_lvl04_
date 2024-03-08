package com.sparta.hh99springlv4.domain.teacher.dto;

import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherResponseDto {
    private String teacherName; //강사이름
    private int teacherCareer; //경력
    private String teacherCompany; //회사
    private String teacherPhone; //전화번호
    private String teacherIntro; //소개

    public TeacherResponseDto(Teacher teacher) {
        this.teacherName = teacher.getTeacherName();
        this.teacherCareer = teacher.getTeacherCareer();
        this.teacherCompany = teacher.getTeacherCompany();
        this.teacherPhone = teacher.getTeacherPhone();
        this.teacherIntro = teacher.getTeacherIntro();
    }
}


