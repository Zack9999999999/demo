package com.example.report.service.impl;

import com.example.report.model.ActivityReportVO;
import com.example.report.repository.ActivityReportReopsitory;
import com.example.report.service.IActivityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityReportService implements IActivityReportService {

    @Autowired
    ActivityReportReopsitory activityReportReopsitory;

    @Override
    public List<ActivityReportVO> getAll() {
        List<ActivityReportVO> reportVOList = activityReportReopsitory.findAll();
        return reportVOList;
    }

    @Override
    public ActivityReportVO findByPrimaryKey(Integer repId) {
        return null;
    }

    @Override
    public void insert(ActivityReportVO activityReportVO) {

    }

    @Override
    public void update(Integer repId, ActivityReportVO activityReportVO) {

    }

    @Override
    public void delete(Integer repId) {

    }
}