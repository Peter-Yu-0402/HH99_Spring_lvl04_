package com.sparta.hh99springlv4.lecture.dto;

import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.OrderByEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



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
    public static class ReadLectureRequestDto {
        private Long id;
        private String lectureName;
    }

    @AllArgsConstructor
    @Getter
    public static class FindLectureRequestDto {
        private String lectureCategory;
        private OrderByEnum orderByEnum;
        private boolean orderBy = false;
    }
}
