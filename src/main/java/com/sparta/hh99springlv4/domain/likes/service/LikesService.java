package com.sparta.hh99springlv4.domain.likes.service;

import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.domain.likes.repository.LikesRepository;
import com.sparta.hh99springlv4.domain.likes.dto.LikesResponseDto;
import com.sparta.hh99springlv4.domain.likes.entity.Likes;
import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.global.handler.exception.NotFoundException;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "LikesService")
@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final LectureRepository lectureRepository;

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

