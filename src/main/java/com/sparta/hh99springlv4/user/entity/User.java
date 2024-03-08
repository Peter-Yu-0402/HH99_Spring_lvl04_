package com.sparta.hh99springlv4.user.entity;

import com.sparta.hh99springlv4.comment.entity.Comment;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.user.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userGender;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String userAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

//    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
//    private List<Lecture> lectureList = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(SignupRequestDto signupRequestDto, String userPassword, UserRoleEnum role) {
        this.userEmail = signupRequestDto.getUserEmail();
        this.userPassword = userPassword;
        this.userGender = signupRequestDto.getUserGender();
        this.userPhone = signupRequestDto.getUserPhone();
        this.userAddress = signupRequestDto.getUserAddress();
        this.role = role;
    }
}


