package com.example.commentreport.repository;

import com.example.commentreport.constant.CommentReportRepTitle;
import com.example.commentreport.model.CommentReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReportRepository extends JpaRepository<CommentReport, Integer> {

    //ENUM篩選
    Page<CommentReport> findByRepTitle(CommentReportRepTitle commentReportRepTitle, Pageable pageable);
    //依照狀態查詢
    Page<CommentReport> findByRepStatus(Byte repStatus, Pageable pageable);

    Page<CommentReport> findAll(Pageable pageable);
}
