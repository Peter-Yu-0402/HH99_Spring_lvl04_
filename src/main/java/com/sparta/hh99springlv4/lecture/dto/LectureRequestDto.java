package com.sparta.hh99springlv4.lecture.dto;

import com.sparta.hh99springlv4.teacher.entity.Teacher;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LectureRequestDto {
    private String lectureName; //강의명
    private Long price; //가격
    private String introL; //소개
    private String category; //카테고리
    private String teacherName; //강사이름
//    Timestamped를 통해 자동으로 입력 받기 때문에 LectureRequestDto에서 제외
//    private LocalDate registrationDate; //등록일



}
