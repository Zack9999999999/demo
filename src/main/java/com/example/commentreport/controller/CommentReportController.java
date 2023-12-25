package com.example.commentreport.controller;

import com.example.commentreport.constant.CommentReportRepTitle;
import com.example.commentreport.dto.CommentReportQueryParams;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReportVO;
import com.example.commentreport.service.ICommentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
public class CommentReportController {

    @Autowired
    private ICommentReportService commentReportService;

    @GetMapping("/c")
    public ResponseEntity<Page<CommentReportVO>> getCommentReports(
            //查詢條件
            @RequestParam(required = false) CommentReportRepTitle commentReportRepTitle, //( )再看要不要加value = "xxx"
            @RequestParam(required = false) Byte repStatus,
            @PageableDefault(size = 5) Pageable pageable
    ) {

        CommentReportQueryParams commentReportQueryParams = new CommentReportQueryParams();
        commentReportQueryParams.setCommentReportRepTitle(commentReportRepTitle);
        commentReportQueryParams.setRepStatus(repStatus);

        Page<CommentReportVO> commentReportList = commentReportService.getCommentReports(commentReportQueryParams, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(commentReportList);
    }

    @GetMapping("/c/{repId}")
    public ResponseEntity<CommentReportVO> getCommentReport(@PathVariable Integer repId) {

        CommentReportVO commentReport = commentReportService.getCommentReportById(repId);

        if (commentReport != null) {
            return ResponseEntity.status(HttpStatus.OK).body(commentReport);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/c")
    public ResponseEntity<String> createCommentReport(@RequestBody @Valid CommentReportRequest commentReportRequest) {
        try {
            Integer commentReportId = commentReportService.createCommentReport(commentReportRequest);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("檢舉得好！這是本網站第 " + commentReportId + "筆檢舉紀錄！");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/c/{repId}")
    public ResponseEntity<CommentReportVO> updateCommentReport(@PathVariable Integer repId,
                                                               @RequestBody CommentReportStatus commentReportStatus) {
        CommentReportVO updateCommentReport = commentReportService.updateCommentReport(repId, commentReportStatus);

        return ResponseEntity.status(HttpStatus.OK).body(updateCommentReport);
    }

}
