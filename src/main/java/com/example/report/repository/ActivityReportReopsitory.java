package com.example.report.repository;

import com.example.report.model.ActivityReportVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ActivityReportReopsitory extends JpaRepository<ActivityReportVO, Integer> {

    @Query("SELECT a FROM ActivityReportVO a JOIN FETCH a.act WHERE a.repId = :repId")
    Optional<ActivityReportVO> findByRepIdAndFetchActEagerly(@Param("repId") Integer repId);
}
