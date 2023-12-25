package com.example.report.service;

import com.example.report.model.ActivityReportVO;

import java.util.List;

public interface IActivityReportService {

	List<ActivityReportVO> getAll();

	ActivityReportVO findByPrimaryKey(Integer repId);

	void insert(ActivityReportVO activityReportVO);

	void update(ActivityReportVO activityReportVO);

	void delete(Integer repId);
}
