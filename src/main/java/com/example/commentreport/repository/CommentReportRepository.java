package com.example.commentreport.repository;

import com.example.commentreport.constant.CommentReportRepTitle;
import com.example.commentreport.model.CommentReportVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReportRepository extends JpaRepository<CommentReportVO, Integer> {

    //ENUM篩選
    Page<CommentReportVO> findByRepTitle(CommentReportRepTitle commentReportRepTitle, Pageable pageable);
    //依照狀態查詢
    Page<CommentReportVO> findByRepStatus(Byte repStatus, Pageable pageable);

    Page<CommentReportVO> findAll(Pageable pageable);
}
