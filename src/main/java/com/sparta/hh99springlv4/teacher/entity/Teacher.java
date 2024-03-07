package com.sparta.hh99springlv4.teacher.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.teacher.dto.TeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"teacher_name", "career", "company", "phone", "introduction"})
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String teacherName;

    @Column(nullable = false)
    private int teacherCareer;

    @Column(nullable = false)
    private String teacherCompany;

    @Column(nullable = false, unique = true)
    private String teacherPhone;

    @Column(nullable = false)
    private String teacherIntro;




    @OneToMany(mappedBy = "teacher")
//    @JsonIgnore
    private List<Lecture> lectureList = new ArrayList<>();

//    public void addLectureList(Lecture lecture) {
//        this.lectureList.add(lecture);
//        lecture.setTeacher(this);
//    }
//
//    // 추가된 생성자
//    public Teacher(Long id) {
//        this.id = id;
//    }


    public Teacher(TeacherRequestDto teacherRequestDto) {
        this.teacherName = teacherRequestDto.getTeacherName();
        this.teacherCareer = teacherRequestDto.getTeacherCareer();
        this.teacherCompany = teacherRequestDto.getTeacherCompany();
        this.teacherPhone = teacherRequestDto.getTeacherPhone();
        this.teacherIntro = teacherRequestDto.getTeacherIntro();
    }

}
