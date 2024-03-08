package com.sparta.hh99springlv4.likes.service;

import com.sparta.hh99springlv4.lecture.dto.LectureRequestDto;
import com.sparta.hh99springlv4.lecture.dto.LectureResponseDto;
import com.sparta.hh99springlv4.lecture.entity.Lecture;
import com.sparta.hh99springlv4.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.likes.dto.LikesResponseDto;
import com.sparta.hh99springlv4.likes.entity.Likes;
import com.sparta.hh99springlv4.likes.repository.LikesRepository;
import com.sparta.hh99springlv4.teacher.entity.Teacher;
import com.sparta.hh99springlv4.user.entity.User;
import com.sparta.hh99springlv4.user.exception.NotFoundException;
import com.sparta.hh99springlv4.user.repository.UserRepository;
import com.sparta.hh99springlv4.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j(topic = "LikesService")
@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;


    @Transactional
    public LikesResponseDto likeLecture(Long lectureId, UserDetailsImpl userDetails) {
        Likes likes;
        // 이미 likes 객체 존재하는지 확인
        if (likesRepository.existsByLectureIdAndUserId(lectureId, userDetails.getUser().getId())) {
            likes = likesRepository.findByLectureIdAndUserId(lectureId, userDetails.getUser().getId());
            likes.changeLikes();
            // DB에 저장
            Likes saveLikes = likesRepository.save(likes);
            // Entity -> ResponseDto
            LikesResponseDto likesResponseDto = new LikesResponseDto(saveLikes);

            return likesResponseDto;
        } else {
            likes = new Likes();
            // Lecture 객체
            Lecture lecture = lectureRepository
                    .findById(lectureId)
                    .orElseThrow(() ->
                            new NotFoundException("Not found the lecture named with" + lectureId));

            // User 객체
            User user = userDetails.getUser();

            // 연관관계 설정
            likes.setLecture(lecture);
            likes.setUser(user);

            // DB에 저장
            Likes saveLikes = likesRepository.save(likes);

            // Entity -> ResponseDto
            LikesResponseDto likesResponseDto = new LikesResponseDto(saveLikes);

            return likesResponseDto;
        }
    }
}

//    @Transactional
//    public void dislikeLecture(Long lectureId, UserDetailsImpl userDetails) {
//        // Likes 객체
//        Likes likes = likesRepository
//                .findByLectureIdAndUserId(lectureId, userDetails.getUser().getId())
//                .orElseThrow(()->
//                        new NotFoundException("Not found the lecture named with" + lectureId));
//
//        // DB에 삭제
//        likesRepository.delete(likes);
//    }

