package com.example.report.service;

import com.example.report.dto.ActivityReportRequest;
import com.example.report.dto.ReportStatus;
import com.example.report.model.ActivityReportVO;

import java.util.List;

public interface IActivityReportService {

	List<ActivityReportVO> getAll();

	ActivityReportVO findByPrimaryKey(Integer repId);

	ActivityReportVO insert(ActivityReportRequest activityReportRequest);

	ActivityReportVO update(Integer repId, ReportStatus reportStatus);

	byte[] getPic(Integer repId);

}
