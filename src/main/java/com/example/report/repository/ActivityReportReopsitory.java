package com.example.report.repository;

import com.example.commentreport.model.CommentReportVO;
import com.example.report.model.ActivityReportVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ActivityReportReopsitory extends JpaRepository<ActivityReportVO, Integer> {

    @Query("SELECT a FROM ActivityReportVO a JOIN FETCH a.act WHERE a.repId = :repId")
    Optional<ActivityReportVO> findByRepIdAndFetchActEagerly(@Param("repId") Integer repId);

    @Query("SELECT a.repPic FROM ActivityReportVO  a WHERE a.repId = :repId")
    byte[] findRepPicByRepId(@Param("repId") Integer repId);
    Page<ActivityReportVO> findAll(Pageable pageable);

    //狀態查詢
    Page<ActivityReportVO> findByRepStatus(Byte repStatus, Pageable pageable);

    Page<ActivityReportVO> findByMemId(Integer memId, Pageable pageable);
}
