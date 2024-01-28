package com.example.commentreport.service.impl;

import com.example.comment.model.CommentVO;
import com.example.comment.model.CommentVOForComRep;
import com.example.comment.repository.CommentRepository;
import com.example.commentreport.dto.CommentReportQueryParams;
import com.example.commentreport.dto.CommentReportRequest;
import com.example.commentreport.dto.CommentReportStatus;
import com.example.commentreport.model.CommentReportVO;
import com.example.commentreport.repository.CommentReportRepository;
import com.example.commentreport.service.ICommentReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class CommentReportService implements ICommentReportService {

    @Autowired
    private CommentReportRepository commentReportRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<CommentReportVO> getCommentReports(
            CommentReportQueryParams commentReportQueryParams,
            Pageable pageable) {

        //狀態查詢
        if (commentReportQueryParams.getRepStatus() != null && commentReportQueryParams.getMemId() != null) {
            return commentReportRepository.findByRepStatusAndMemId(commentReportQueryParams.getRepStatus(), commentReportQueryParams.getMemId(), pageable);

        } else if (commentReportQueryParams.getRepStatus() != null) {
            return commentReportRepository.findByRepStatus(commentReportQueryParams.getRepStatus(), pageable);

        } else if (commentReportQueryParams.getMemId() != null) {
            return commentReportRepository.findByMemId(commentReportQueryParams.getMemId(), pageable);
        }

        return commentReportRepository.findAll(pageable);
    }

    @Override
    public CommentReportVO getCommentReportById(Integer repId) {

        return commentReportRepository.findById(repId).orElse(null);

    }

    @Override
    @Transactional
    public CommentReportVO createCommentReport(CommentReportRequest commentReportRequest) {

        CommentVOForComRep comment = commentRepository.findById(commentReportRequest.getComId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        CommentReportVO commentReport = new CommentReportVO();
        BeanUtils.copyProperties(commentReportRequest, commentReport);
        commentReport.setComment(comment);
        commentReport.setRepTime(new Date());

        return commentReportRepository.save(commentReport);
    }

    @Override
    public CommentReportVO updateCommentReport(Integer repId, CommentReportStatus commentReportStatus) {

        Optional<CommentReportVO> commentReport = commentReportRepository.findById(repId);

        if (commentReport.isPresent()) {
            commentReport.get().setRepStatus(commentReportStatus.getRepStatus());

            switch (commentReportStatus.getRepStatus()) {
                case 1:
                    commentReport.get().getComment().setComStatus(Byte.valueOf("1"));
                    break;
                case 2:
                    commentReport.get().getComment().setComStatus((byte) 2); //2=不會顯示留言
                    break;
                case 3:
                    commentReport.get().getComment().setComStatus((byte) 1);
                    break;
            }
            return commentReportRepository.save(commentReport.get());
        } else {
            return null;
        }
    }
}
