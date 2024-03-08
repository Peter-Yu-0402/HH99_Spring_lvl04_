package com.sparta.hh99springlv4.domain.lecture.entity;


import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.domain.likes.entity.Likes;
import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lectureName;

    @Column(nullable = false)
    private Long lecturePrice;

    @Column(nullable = false)
    private String lectureIntro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Enum 값과 매핑
    private CategoryEnum lectureCategory;

    @Column(nullable = false)
    private LocalDate lectureRegistrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "likes", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @Column(nullable = false)
    private String teacherName;


    public Lecture(LectureRequestDto.CreateLectureRequestDto lectureRequestDto) {
        this.lectureName = lectureRequestDto.getLectureName();
        this.lecturePrice = lectureRequestDto.getLecturePrice();
        this.lectureIntro = lectureRequestDto.getLectureIntro();
        this.lectureRegistrationDate = LocalDate.now();
        this.lectureCategory = CategoryEnum.valueOf(lectureRequestDto.getLectureCategory());
        this.teacherName = lectureRequestDto.getTeacherName();
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addLikesList(Likes likes) {
        this.likesList.add(likes);
        likes.setLecture(this);
    }

    public void addCommentList(Comment comment) {
        this.commentList.add(comment);
        comment.setLecture(this);
    }

}
