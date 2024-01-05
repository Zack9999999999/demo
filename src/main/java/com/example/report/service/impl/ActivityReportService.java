package com.example.report.service.impl;

import com.example.act.repository.ActRepository;
import com.example.report.dto.ActivityReportRequest;
import com.example.report.model.ActivityReportVO;
import com.example.report.repository.ActivityReportReopsitory;
import com.example.report.service.IActivityReportService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ActivityReportService implements IActivityReportService {

    @Autowired
    private ActivityReportReopsitory activityReportReopsitory;

    @Autowired
    private ActRepository actRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ActivityReportVO> getAll() {
        actRepository.findAll();

        return activityReportReopsitory.findAll();
    }

    @Override
    public ActivityReportVO findByPrimaryKey(Integer repId) {
        return activityReportReopsitory.findByRepIdAndFetchActEagerly(repId).orElse(null);
    }

    @Override
    public ActivityReportVO insert(ActivityReportRequest activityReportRequest) {

        ActivityReportVO activityReport = new ActivityReportVO();

        modelMapper.map(activityReportRequest, activityReport);

        activityReport.setRepTime(new Date());

        return activityReportReopsitory.save(activityReport);
    }

    @Override
    public ActivityReportVO update(Integer repId, ActivityReportRequest activityReportRequest) {
        ActivityReportVO activityReport = activityReportReopsitory.findById(repId).orElse(null);

        modelMapper.map(activityReportRequest, ActivityReportVO.class);

        return activityReportReopsitory.save(activityReport);
    }

}