package com.sparta.hh99springlv4.lecture.dto;

import com.sparta.hh99springlv4.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.teacher.dto.TeacherResponseDto;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class LectureResponseDto {
    @AllArgsConstructor
    @Getter
    public static class CreateLectureResponseDto {
        private String lectureName; //강의명
        private Long lecturePrice; //가격
        private String lectureIntro; //소개
        private CategoryEnum lectureCategory; // 카테고리
        private LocalDate lectureRegistrationDate;
        private String teacherName; //강사이름


        public CreateLectureResponseDto(Lecture lecture) {
            this.lectureName = lecture.getLectureName();
            this.lecturePrice = lecture.getLecturePrice();
            this.lectureIntro = lecture.getLectureIntro();
            this.lectureCategory = lecture.getLectureCategory();
            this.lectureRegistrationDate = lecture.getLectureRegistrationDate();
            // 다대일 연관관계
            this.teacherName = lecture.getTeacher().getTeacherName();
        }

    }

    @AllArgsConstructor
    @Getter
    public static class SelectLectureResponseDto {
        private String lectureName; // 강의명
        private Long lecturePrice; // 가격
        private String lectureIntro; // 소개
        private CategoryEnum lectureCategory; // 카테고리
        private LocalDate lectureRegistrationDate;
        private String teacherName; // 강사이름
        private int teacherCareer; // 강사 경력
        private String teacherCompany; // 강사 회사
        private String teacherIntro; // 강사 소개

        public SelectLectureResponseDto(Lecture lecture) {
            this.lectureName = lecture.getLectureName();
            this.lecturePrice = lecture.getLecturePrice();
            this.lectureIntro = lecture.getLectureIntro();
            this.lectureCategory = lecture.getLectureCategory();
            this.lectureRegistrationDate = lecture.getLectureRegistrationDate();

            // 다대일 연관관계
            this.teacherName = lecture.getTeacher().getTeacherName();
            this.teacherCareer = lecture.getTeacher().getTeacherCareer();
            this.teacherCompany = lecture.getTeacher().getTeacherCompany();
            this.teacherIntro = lecture.getTeacher().getTeacherIntro();
        }
    }
    @Getter
    public static class ReadLectureResponseDto {
        private final SelectLectureResponseDto lecture;
        private final List<CommentResponseDto> comments;
        private final int likesCount;


        public ReadLectureResponseDto(SelectLectureResponseDto lecture, List<CommentResponseDto> comments, int likesCount) {
            this.lecture = lecture;
            this.comments = comments;
            this.likesCount = likesCount;
        }

        // Getter for lecture
        public SelectLectureResponseDto getLecture() {
            return lecture;
        }

        // Getter for comments
        public List<CommentResponseDto> getComments() {
            return comments;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class FindLectureResponseDto {
        private String lectureName; //강의명
        private Long lecturePrice; //가격
        private String lectureIntro; //소개
        private CategoryEnum lectureCategory; // 카테고리
        private LocalDate lectureRegistrationDate;

        public FindLectureResponseDto(Lecture lecture) {
            this.lectureName = lecture.getLectureName();
            this.lecturePrice = lecture.getLecturePrice();
            this.lectureIntro = lecture.getLectureIntro();
            this.lectureCategory = lecture.getLectureCategory();
            this.lectureRegistrationDate = lecture.getLectureRegistrationDate();
        }
    }
}

