package com.example.comment.repository;

import com.example.comment.model.CommentVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentVO, Integer> {
}
