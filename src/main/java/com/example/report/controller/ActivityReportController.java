package com.example.report.controller;

import com.example.report.constant.ReportTitle;
import com.example.report.dto.ActivityReportRequest;
import com.example.report.model.ActivityReportVO;
import com.example.report.service.IActivityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<ActivityReportVO> insert(
            @RequestParam("actId") Integer actId,
            @RequestParam("memId") Integer memId,
            @RequestParam("repTitle") ReportTitle repTitle,
            @RequestParam("repContent") String repContent,
            @RequestParam(required = false) MultipartFile repPic
    ) {
        ActivityReportRequest activityReportRequest = new ActivityReportRequest();
        activityReportRequest.setActId(actId);
        activityReportRequest.setMemId(memId);
        activityReportRequest.setRepTitle(repTitle);
        activityReportRequest.setRepContent(repContent);
        // 處理文件數據
        if (repPic != null && !repPic.isEmpty()) {
            byte[] repPicBytes = new byte[0]; // 或其他所需的文件處理邏輯
            try {
                repPicBytes = repPic.getBytes();
                activityReportRequest.setRepPic(repPicBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ActivityReportVO activityReport = activityReportService.insert(activityReportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(activityReport);
    }

    @PutMapping("/activityreport/{repId}")
    public ResponseEntity<ActivityReportVO> update(@PathVariable Integer repId,
                                                   @RequestBody ActivityReportRequest activityReportRequest) {
        ActivityReportVO activityReport = activityReportService.update(repId, activityReportRequest);
        return ResponseEntity.status(HttpStatus.OK).body(activityReport);
    }

    @GetMapping("/activityreport/{repId}/pic")
    public ResponseEntity<byte[]> getPic(@PathVariable Integer repId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        byte[] pic = activityReportService.getPic(repId);
        if (pic != null) {
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(pic);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
