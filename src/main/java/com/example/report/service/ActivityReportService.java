package com.example.report.service;

import com.example.report.model.ActivityReport;

import java.util.List;

public interface ActivityReportService {

	List<ActivityReport> getAll();

	ActivityReport findByPrimaryKey(Integer repId);

	void insert(ActivityReport activityReportVO);

	void update(ActivityReport activityReportVO);

	void delete(Integer repId);
}
