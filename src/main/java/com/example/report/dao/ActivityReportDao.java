package com.example.report.dao;


import com.example.report.model.ActivityReport;

import java.util.List;

public interface ActivityReportDao {
	public void insert(ActivityReport activityReportVO);

	public void update(ActivityReport activityReportVO);

	public void delete(Integer repId);

	public ActivityReport findByPrimaryKey(Integer repId);

	public List<ActivityReport> getAll();
}
