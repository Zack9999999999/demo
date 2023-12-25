package com.example.commentreport.service.impl;

import com.example.comment.model.Comment;
import com.example.comment.repository.CommentRepository;
import com.example.commentreport.dto.CommentReportQueryParams;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReport;
import com.example.commentreport.repository.CommentReportRepository;
import com.example.commentreport.service.CommentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CommentReportServiceImpl implements CommentReportService {

    @Autowired
    private CommentReportRepository commentReportRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<CommentReport> getCommentReports(
            CommentReportQueryParams commentReportQueryParams, Pageable pageable) {
        //依照檢舉標題查詢
        if (commentReportQueryParams.getCommentReportRepTitle() != null) {
            return commentReportRepository.findByRepTitle(
                    commentReportQueryParams.getCommentReportRepTitle(), pageable);
        }
        //依照狀態查詢
        if (commentReportQueryParams.getRepStatus() != null) {
            return commentReportRepository.findByRepStatus(commentReportQueryParams.getRepStatus(), pageable);
        }

        Page<CommentReport> page = commentReportRepository.findAll(pageable);

        //这段代码通常只有在您需要在返回之前处理或使用这些值时才有用。
        // 例如，如果您需要日志记录这些值，或者您需要基于这些值进一步处理数据
//        int totalPages = page.getTotalPages(); //總頁數
//        long totalElements = page.getTotalElements(); //總數量
//        List<CommentReport> content = page.getContent(); //當前頁面數據

        return commentReportRepository.findAll(pageable);
    }

    @Override
    public CommentReport getCommentReportById(Integer repId) {

        return commentReportRepository.findById(repId).orElse(null);

//        return commentReportRepository.findById(repId)
//                .orElseThrow(() -> new EntityNotFoundException("CommentReport not found for id: " + repId));
    }

    @Override
    @Transactional
    public Integer createCommentReport(CommentReportRequest commentReportRequest) {

        Comment comment = commentRepository.findById(commentReportRequest.getComId())
                .orElseThrow(() -> new NoSuchElementException("別胡搞瞎搞"));

//        Optional<Comment> comment = commentRepository.findById(commentReportRequest.getComId());

        CommentReport commentReport = new CommentReport();
//        commentReport.setComment(comment.get()); //.NoSuchElementException No value present

//        commentReport.getComment().setComId(commentReportRequest.getComId()); //會報錯NUll 為什麼

        commentReport.setComment(comment);
        commentReport.setMemId(commentReportRequest.getMemId());
        commentReport.setRepTitle(commentReportRequest.getRepTitle());
        commentReport.setRepContent(commentReportRequest.getRepContent());

        Date now = new Date();
        commentReport.setRepTime(now);

        return commentReportRepository.save(commentReport).getRepId();

//        CommentReport saveCommentReport = commentReportRepository.save(commentReport);
//        return saveCommentReport.getRepId();
    }

    @Override
    public CommentReport updateCommentReport(Integer repId, CommentReportStatus commentReportStatus) {

        Optional<CommentReport> commentReport = commentReportRepository.findById(repId);

        if (commentReport.isPresent()) {
            commentReport.get().setRepStatus(commentReportStatus.getRepStatus());
            return commentReportRepository.save(commentReport.get());
        } else {
            return null;
        }
    }
}
