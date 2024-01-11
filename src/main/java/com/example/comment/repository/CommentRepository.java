package com.example.comment.repository;

import com.example.comment.model.CommentVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentVO, Integer> {
}
