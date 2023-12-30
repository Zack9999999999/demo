package com.example.report.repository;

import com.example.report.model.ActivityReportVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityReportReopsitory extends JpaRepository<ActivityReportVO, Integer> {
}
