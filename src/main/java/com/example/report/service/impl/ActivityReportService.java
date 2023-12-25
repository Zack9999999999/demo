package com.example.report.service.impl;

import com.example.report.dao.IActivityReport;
import com.example.report.dao.impl.ActivityReportDAO;
import com.example.report.model.ActivityReportVO;
import com.example.report.service.IActivityReportService;

import java.util.List;

public class ActivityReportService implements IActivityReportService {

	private IActivityReport activityReportDAO;

	public ActivityReportService() {
		activityReportDAO = new ActivityReportDAO();
	}

	public List<ActivityReportVO> getAll() {
		return activityReportDAO.getAll();
	}

	public ActivityReportVO findByPrimaryKey(Integer repId) {
		return activityReportDAO.findByPrimaryKey(repId);
	}

	public void insert(ActivityReportVO activityReportVO) {
		activityReportDAO.insert(activityReportVO);
	}

	public void update(ActivityReportVO activityReportVO) {
		activityReportDAO.update(activityReportVO);
	}

	public void delete(Integer repId) {
		activityReportDAO.delete(repId);
	}

}