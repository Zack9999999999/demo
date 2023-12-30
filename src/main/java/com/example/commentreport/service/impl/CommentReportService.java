package com.example.commentreport.service.impl;

import com.example.comment.model.CommentVO;
import com.example.comment.repository.CommentRepository;
import com.example.commentreport.dto.CommentReportQueryParams;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReportVO;
import com.example.commentreport.repository.CommentReportRepository;
import com.example.commentreport.service.ICommentReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CommentReportService implements ICommentReportService {

    @Autowired
    private CommentReportRepository commentReportRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<CommentReportVO> getCommentReports(
            CommentReportQueryParams commentReportQueryParams, Pageable pageable) {
        //檢舉標題查詢
        if (commentReportQueryParams.getCommentReportRepTitle() != null) {
            return commentReportRepository.findByRepTitle(
                    commentReportQueryParams.getCommentReportRepTitle(), pageable);
        }
        //狀態查詢
        if (commentReportQueryParams.getRepStatus() != null) {
            return commentReportRepository.findByRepStatus(commentReportQueryParams.getRepStatus(), pageable);
        }

        //这段代码通常只有在您需要在返回之前处理或使用这些值时才有用。
        // 例如，如果您需要日志记录这些值，或者您需要基于这些值进一步处理数据
//        Page<CommentReport> page = commentReportRepository.findAll(pageable);
//        int totalPages = page.getTotalPages(); //總頁數
//        long totalElements = page.getTotalElements(); //總數量
//        List<CommentReport> content = page.getContent(); //當前頁面數據

        return commentReportRepository.findAll(pageable);
    }

    @Override
    public CommentReportVO getCommentReportById(Integer repId) {

        return commentReportRepository.findById(repId).orElse(null);

    }

    @Override
    @Transactional
    public CommentReportVO createCommentReport(CommentReportRequest commentReportRequest) {

        CommentVO comment = commentRepository.findById(commentReportRequest.getComId())
                .orElseThrow(() -> new NoSuchElementException("別胡搞瞎搞"));

        CommentReportVO commentReport = new CommentReportVO();
        BeanUtils.copyProperties(commentReportRequest,commentReport);
        commentReport.setComment(comment);
        commentReport.setRepTime(new Date());
//        commentReport.setMemId(commentReportRequest.getMemId());
//        commentReport.setRepTitle(commentReportRequest.getRepTitle());
//        commentReport.setRepContent(commentReportRequest.getRepContent());

        return commentReportRepository.save(commentReport);
    }

    @Override
    public CommentReportVO updateCommentReport(Integer repId, CommentReportStatus commentReportStatus) {

        Optional<CommentReportVO> commentReport = commentReportRepository.findById(repId);

        if (commentReport.isPresent()) {
            commentReport.get().setRepStatus(commentReportStatus.getRepStatus());
            return commentReportRepository.save(commentReport.get());
        } else {
            return null;
        }
    }
}
