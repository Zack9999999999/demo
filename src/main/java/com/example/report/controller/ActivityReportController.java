package com.example.report.controller;

import com.example.report.dao.IActivityReportDAO;
import com.example.report.model.ActivityReportVO;
import com.example.report.service.IActivityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.MultipartConfig;
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
        return ResponseEntity.status(HttpStatus.OK).body(activityReport);
    }

    @PostMapping("/activityreport")
    public ResponseEntity<?> insert(@RequestBody ActivityReportVO activityReport) {
        activityReportService.insert(activityReport);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/activityreort/{repId}")
    public ResponseEntity<?> update(@PathVariable Integer repId,
                                    @RequestBody ActivityReportVO activityReport) {
        activityReportService.update(repId, activityReport);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
