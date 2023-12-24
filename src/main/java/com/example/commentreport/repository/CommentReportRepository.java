package com.example.commentreport.repository;

import com.example.commentreport.model.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReportRepository extends JpaRepository<CommentReport, Integer> {
}
