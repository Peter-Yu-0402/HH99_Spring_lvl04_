package com.sparta.hh99springlv4.teacher.repository;

import com.sparta.hh99springlv4.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findById(Long id);

    Teacher findByTeacherName(String teacherName);

}
