package com.sparta.hh99springlv4.domain.lecture.dto;

import com.sparta.hh99springlv4.domain.lecture.entity.LectureByEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class LectureRequestDto {

    @AllArgsConstructor
    @Getter
    public static class CreateLectureRequestDto {
        private String lectureName; //강의명
        private Long lecturePrice; //가격
        private String lectureIntro; //소개
        private String lectureCategory; //카테고리
        private String teacherName; //강사이름
    }

    @AllArgsConstructor
    @Getter
    public static class FindLectureRequestDto {
        private String lectureCategory;
        private LectureByEnum lectureByEnum;
        private boolean orderBy = false;
    }
}
