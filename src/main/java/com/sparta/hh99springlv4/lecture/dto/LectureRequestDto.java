package com.sparta.hh99springlv4.lecture.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LectureRequestDto {
    private String lectureName; //강의명
    private Long lecturePrice; //가격
    private String lectureIntro; //소개
    private String lectureCategory; //카테고리
    private String teacherName; //강사이름


}
