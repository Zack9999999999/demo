package com.example.comment.service.impl;

import com.example.comment.dao.ICommentDAO;
import com.example.comment.dto.CommentQueryParams;
import com.example.comment.dto.CommentRequest;
import com.example.comment.service.ICommentService;
import com.example.comment.model.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    @Override
    public List<CommentVO> getComments() {
        return commentDAO.getComments();
    }

    @Override
    public CommentVO getCommentById(Integer comId) {
        return commentDAO.getCommentById(comId);
    }

    @Override
    public List<CommentVO> insertComment(CommentRequest commentRequest) {
        return commentDAO.insertComment(commentRequest);
    }

    @Override
    public void updateComment(Integer comId, CommentRequest commentRequest) {

        commentDAO.updateComment(comId, commentRequest);
    }

    @Override
    public void deleteComment(Integer comId) {
        commentDAO.deleteComment(comId);
    }
}
