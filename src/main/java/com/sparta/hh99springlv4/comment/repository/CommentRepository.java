package com.sparta.hh99springlv4.comment.repository;

import com.sparta.hh99springlv4.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
