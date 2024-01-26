package com.example.report.service.impl;

import com.example.act.repository.ActRepository;
import com.example.report.dto.ActivityReportQueryParams;
import com.example.report.dto.ActivityReportRequest;
import com.example.report.dto.ReportStatus;
import com.example.report.model.ActivityReportVO;
import com.example.report.repository.ActivityReportReopsitory;
import com.example.report.service.IActivityReportService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ActivityReportVO> getAll(ActivityReportQueryParams activityReportQueryParams, Pageable pageable) {

        if (activityReportQueryParams.getRepStatus() != null) {
            return activityReportReopsitory.findByRepStatus(activityReportQueryParams.getRepStatus(), pageable);
        }
//        if (activityReportQueryParams.getMemId() != null) {
//            return activityReportReopsitory.findByMemId(activityReportQueryParams.getMemId(), pageable);
//        }

        return activityReportReopsitory.findAll(pageable);
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
    public ActivityReportVO update(Integer repId, ReportStatus reportStatus) {
        ActivityReportVO activityReport = activityReportReopsitory.findById(repId).orElse(null);
//        modelMapper.map(activityReportRequest, ActivityReportVO.class);

        if (activityReport != null) {
            activityReport.setRepStatus(reportStatus.getRepStatus());
            activityReport.getAct().setActStatus(reportStatus.getRepStatus());

            return activityReportReopsitory.save(activityReport);
        } else {
            return null;
        }
    }

    @Override
    public byte[] getPic(Integer repId) {
        return activityReportReopsitory.findRepPicByRepId(repId);
    }


}