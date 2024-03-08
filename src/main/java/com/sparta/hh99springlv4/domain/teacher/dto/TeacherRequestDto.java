package com.sparta.hh99springlv4.domain.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequestDto {
    private String teacherName; //강사이름
    private int teacherCareer; //경력
    private String teacherCompany; //회사
    private String teacherPhone; //전화번호
    private String teacherIntro; //소개

}
