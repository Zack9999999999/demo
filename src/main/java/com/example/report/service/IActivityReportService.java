package com.example.report.service;

import com.example.report.dto.ActivityReportRequest;
import com.example.report.dto.ReportStatus;
import com.example.report.model.ActivityReportVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IActivityReportService {

	Page<ActivityReportVO> getAll(Byte repStatus, Pageable pageable);

	ActivityReportVO findByPrimaryKey(Integer repId);

	ActivityReportVO insert(ActivityReportRequest activityReportRequest);

	ActivityReportVO update(Integer repId, ReportStatus reportStatus);

	byte[] getPic(Integer repId);

}
