package com.example.comment.repository;

import com.example.comment.model.CommentVO;
import com.example.comment.model.CommentVOForComRep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentVOForComRep, Integer> {
}
