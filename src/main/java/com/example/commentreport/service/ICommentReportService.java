package com.example.commentreport.service;

import com.example.commentreport.dto.CommentReportQueryParams;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReportVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICommentReportService {

    Page<CommentReportVO> getCommentReports(CommentReportQueryParams commentReportQueryParams, Pageable pageable);

    CommentReportVO getCommentReportById(Integer repId);

    Integer createCommentReport(CommentReportRequest commentReportRequest);

    CommentReportVO updateCommentReport(Integer repId, CommentReportStatus commentReportStatus);

}
