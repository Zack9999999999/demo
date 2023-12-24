package com.example.commentreport.service.impl;

import com.example.comment.model.Comment;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.repository.CommentReportRepository;
import com.example.commentreport.model.CommentReport;
import com.example.comment.repository.CommentRepository;
import com.example.commentreport.service.CommentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CommentReportServiceImpl implements CommentReportService {

    @Autowired
    private CommentReportRepository commentReportRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentReport> getCommentReports() {

        return commentReportRepository.findAll(); //取不到Comment的com_id
    }

    @Override
    public CommentReport getCommentReportById(Integer repId) {

        Optional<CommentReport> commentReport = commentReportRepository.findById(repId);

        if (commentReport.isPresent()) {
            // 如果Optional包含物件，则返回
            return commentReport.get();
        } else {
            return null;
        }

//        return commentReportRepository.findById(repId)
//                .orElseThrow(() -> new EntityNotFoundException("CommentReport not found for id: " + repId));
    }

    @Override
    public Integer createCommentReport(CommentReportRequest commentReportRequest) {

        Comment comment = commentRepository.findById(commentReportRequest.getComId())
                .orElseThrow(() -> new RuntimeException("別鬧了，沒有這個留言"));

        CommentReport commentReport = new CommentReport();

        commentReport.setComment(comment); //怪怪
        commentReport.setMemId(commentReportRequest.getMemId());
        commentReport.setRepTitle(commentReportRequest.getRepTitle());
        commentReport.setRepContent(commentReportRequest.getRepContent());

        Date now = new Date();
        commentReport.setRepTime(now);

        CommentReport saveCommentReport = commentReportRepository.save(commentReport);

        return saveCommentReport.getRepId();
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
