package com.sparta.hh99springlv4.domain.likes.repository;

import com.sparta.hh99springlv4.domain.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllByLectureId(Long lectureId);
    Likes findByLectureIdAndUserId(Long lectureId, Long userId);
    Boolean existsByLectureIdAndUserId(Long lectureId, Long userId);
}
