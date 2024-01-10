package com.example.commentreport.controller;

import com.example.commentreport.constant.CommentReportRepTitle;
import com.example.commentreport.dto.CommentReportQueryParams;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReportVO;
import com.example.commentreport.service.ICommentReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class CommentReportController {

    @Autowired
    private ICommentReportService commentReportService;

    @GetMapping("/commentreport")
    public ResponseEntity<Page<CommentReportVO>> getCommentReports(
            @RequestParam(required = false) Byte repStatus,
            @RequestParam(required = false) String sortDirection,
            @PageableDefault(size = 5, sort = "repTime", direction = Sort.Direction.ASC) Pageable pageable
    ) {

        if ("DESC".equalsIgnoreCase(sortDirection)) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "repTime")
            );
        }

log.info(pageable.toString());

        CommentReportQueryParams commentReportQueryParams = new CommentReportQueryParams();
        commentReportQueryParams.setRepStatus(repStatus);

        Page<CommentReportVO> commentReportList = commentReportService.getCommentReports(commentReportQueryParams, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(commentReportList);
    }

    @GetMapping("/commentreport/{repId}")
    public ResponseEntity<CommentReportVO> getCommentReport(@PathVariable Integer repId) {

        CommentReportVO commentReport = commentReportService.getCommentReportById(repId);

        if (commentReport != null) {
            return ResponseEntity.status(HttpStatus.OK).body(commentReport);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/commentreport")
    public ResponseEntity<CommentReportVO> createCommentReport(@RequestBody @Valid CommentReportRequest
                                                                       commentReportRequest) {
        CommentReportVO commentReport = commentReportService.createCommentReport(commentReportRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentReport);
    }

    @PutMapping("/commentreport/{repId}")
    public ResponseEntity<CommentReportVO> updateCommentReport(@PathVariable Integer repId,
                                                               @RequestBody CommentReportStatus commentReportStatus) {

        CommentReportVO updateCommentReport = commentReportService.updateCommentReport(repId, commentReportStatus);

        return ResponseEntity.status(HttpStatus.OK).body(updateCommentReport);
    }

}
