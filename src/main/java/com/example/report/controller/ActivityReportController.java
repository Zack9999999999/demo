package com.example.report.controller;

import com.example.report.dto.ActivityReportRequest;
import com.example.report.model.ActivityReportVO;
import com.example.report.service.IActivityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityReportController {

    @Autowired
    private IActivityReportService activityReportService;

    @GetMapping("/activityreport")
    public ResponseEntity<List<ActivityReportVO>> getAll() {
        List<ActivityReportVO> reportVOList = activityReportService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(reportVOList);
    }

    @GetMapping("/activityreport/{repId}")
    public ResponseEntity<ActivityReportVO> findByPrimaryKey(@PathVariable Integer repId) {
        ActivityReportVO activityReport = activityReportService.findByPrimaryKey(repId);
        if (activityReport != null) {
            return ResponseEntity.status(HttpStatus.OK).body(activityReport);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/activityreport")
    public ResponseEntity<ActivityReportVO> insert(@RequestBody ActivityReportRequest activityReportRequest) {
        ActivityReportVO activityReport = activityReportService.insert(activityReportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(activityReport);
    }

    @PutMapping("/activityreport/{repId}")
    public ResponseEntity<ActivityReportVO> update(@PathVariable Integer repId,
                                    @RequestBody ActivityReportRequest activityReportRequest) {
        ActivityReportVO activityReport = activityReportService.update(repId, activityReportRequest);
        return ResponseEntity.status(HttpStatus.OK).body(activityReport);
    }



}
