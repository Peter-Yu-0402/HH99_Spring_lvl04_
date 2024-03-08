package com.sparta.hh99springlv4.domain.comment.repository;

import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
