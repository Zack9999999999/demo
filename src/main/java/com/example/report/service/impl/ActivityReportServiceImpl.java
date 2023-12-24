package com.example.report.service.impl;

import com.example.report.dao.ActivityReportDao;
import com.example.report.dao.impl.ActivityReportDaoImpl;
import com.example.report.model.ActivityReport;
import com.example.report.service.ActivityReportService;

import java.util.List;

public class ActivityReportServiceImpl implements ActivityReportService {

	private ActivityReportDao activityReportDAO;

	public ActivityReportServiceImpl() {
		activityReportDAO = new ActivityReportDaoImpl();
	}

	public List<ActivityReport> getAll() {
		return activityReportDAO.getAll();
	}

	public ActivityReport findByPrimaryKey(Integer repId) {
		return activityReportDAO.findByPrimaryKey(repId);
	}

	public void insert(ActivityReport activityReportVO) {
		activityReportDAO.insert(activityReportVO);
	}

	public void update(ActivityReport activityReportVO) {
		activityReportDAO.update(activityReportVO);
	}

	public void delete(Integer repId) {
		activityReportDAO.delete(repId);
	}

}