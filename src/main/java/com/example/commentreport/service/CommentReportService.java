package com.example.commentreport.service;

import com.example.commentreport.dto.CommentReportQueryParams;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentReportService {

    Page<CommentReport> getCommentReports(CommentReportQueryParams commentReportQueryParams, Pageable pageable);

    CommentReport getCommentReportById(Integer repId);

    Integer createCommentReport(CommentReportRequest commentReportRequest);

    CommentReport updateCommentReport(Integer repId, CommentReportStatus commentReportStatus);

}
