package com.example.actreport.service;

import com.example.actreport.dto.ActivityReportQueryParams;
import com.example.actreport.dto.ActivityReportRequest;
import com.example.actreport.dto.ReportStatus;
import com.example.actreport.model.ActivityReportVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IActivityReportService {

	Page<ActivityReportVO> getAll(ActivityReportQueryParams activityReportQueryParams, Pageable pageable);

	ActivityReportVO findByPrimaryKey(Integer repId);

	ActivityReportVO insert(ActivityReportRequest activityReportRequest);

	ActivityReportVO update(Integer repId, ReportStatus reportStatus);

	byte[] getPic(Integer repId);

}
