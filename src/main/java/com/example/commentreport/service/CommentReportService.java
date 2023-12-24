package com.example.commentreport.service;

import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReport;

import java.util.List;

public interface CommentReportService {

    List<CommentReport> getCommentReports();

    CommentReport getCommentReportById(Integer repId);

    Integer createCommentReport(CommentReportRequest commentReportRequest);

    CommentReport updateCommentReport(Integer repId, CommentReportStatus commentReportStatus);

}
