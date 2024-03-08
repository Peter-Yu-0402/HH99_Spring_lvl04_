package com.sparta.hh99springlv4.domain.teacher.entity;

import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.teacher.dto.TeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Lecture> lectureList = new ArrayList<>();

    public Teacher(TeacherRequestDto teacherRequestDto) {
        this.teacherName = teacherRequestDto.getTeacherName();
        this.teacherCareer = teacherRequestDto.getTeacherCareer();
        this.teacherCompany = teacherRequestDto.getTeacherCompany();
        this.teacherPhone = teacherRequestDto.getTeacherPhone();
        this.teacherIntro = teacherRequestDto.getTeacherIntro();
    }

    public void updateTeacher(TeacherRequestDto teacherRequestDto) {
        this.teacherName = teacherRequestDto.getTeacherName();
        this.teacherCareer = teacherRequestDto.getTeacherCareer();
        this.teacherCompany = teacherRequestDto.getTeacherCompany();
        this.teacherPhone = teacherRequestDto.getTeacherPhone();
        this.teacherIntro = teacherRequestDto.getTeacherIntro();
    }
}
