package com.sparta.hh99springlv4.likes.repository;

import com.sparta.hh99springlv4.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllByLectureId(Long lectureId);
    Likes findByLectureIdAndUserId(Long lectureId, Long userId);
    Boolean existsByLectureIdAndUserId(Long lectureId, Long userId);
}
