package com.example.comment.service.impl;

import com.example.comment.dao.ICommentDAO;
import com.example.comment.dto.CommentQueryParams;
import com.example.comment.dto.CommentRequest;
import com.example.comment.dto.CommentStatus;
import com.example.comment.service.ICommentService;
import com.example.comment.model.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    @Override
    public Integer countComments(CommentQueryParams commentQueryParams) {
        return commentDAO.countComments(commentQueryParams);
    }

    @Override
    public List<CommentVO> getComments(CommentQueryParams commentQueryParams) {
        return commentDAO.getComments(commentQueryParams);
    }

    @Override
    public CommentVO getCommentById(Integer comId) {
        return commentDAO.getCommentById(comId);
    }

    @Override
    @Transactional
    public Integer insertComment(CommentRequest commentRequest) {
        return commentDAO.insertComment(commentRequest);
    }

    @Override
    @Transactional
    public void updateComment(Integer comId, CommentRequest commentRequest) {
        commentDAO.updateComment(comId, commentRequest);
    }

    @Override
    public void deleteComment(Integer comId, CommentStatus commentStatus) {
        commentDAO.deleteComment(comId, commentStatus);
    }
}
