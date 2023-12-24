package com.example.commentreport.controller;

import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReport;
import com.example.commentreport.service.CommentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentReportController {

    @Autowired
    private CommentReportService commentReportService;

    @GetMapping("/c")
    public ResponseEntity<List<CommentReport>> getCommentReports() {

        List<CommentReport> commentReportList = commentReportService.getCommentReports();

        return ResponseEntity.status(HttpStatus.OK).body(commentReportList);
    }

    @GetMapping("/c/{repId}")
    public ResponseEntity<CommentReport> getCommentReport(@PathVariable Integer repId) {

        CommentReport commentReport = commentReportService.getCommentReportById(repId);

        if (commentReport != null) {
            return ResponseEntity.status(HttpStatus.OK).body(commentReport);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/c")
    public ResponseEntity<String> createCommentReport(@RequestBody @Valid CommentReportRequest commentReportRequest) {

        Integer commentReportId = commentReportService.createCommentReport(commentReportRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("檢舉得好！這是本網站第 " + commentReportId + "筆檢舉紀錄！");
    }

    @PutMapping("/c/{repId}")
    public ResponseEntity<CommentReport> updateCommentReport(@PathVariable Integer repId,
                                                             @RequestBody CommentReportStatus commentReportStatus) {
        CommentReport updateCommentReport = commentReportService.updateCommentReport(repId, commentReportStatus);

        return ResponseEntity.status(HttpStatus.OK).body(updateCommentReport);
    }

}
