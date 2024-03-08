package com.sparta.hh99springlv4.domain.lecture.repository;

import com.sparta.hh99springlv4.domain.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.lecture.entity.LectureByEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findByLectureCategoryOrderByLectureNameDesc(CategoryEnum lectureCategory);
    List<Lecture> findByLectureCategoryOrderByLecturePriceDesc(CategoryEnum lectureCategory);
    List<Lecture> findByLectureCategoryOrderByLectureRegistrationDateDesc(CategoryEnum lectureCategory);

    Optional<Lecture> findById(Long id);

}
