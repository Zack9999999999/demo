package com.example.comment.service.impl;

import com.example.comment.dao.CommentDAO;
import com.example.comment.dto.CommentRequest;
import com.example.comment.service.CommentService;
import com.example.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentServiceimpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public List<Comment> getComments() {
        return commentDAO.getComments();
    }

    @Override
    public Comment getCommentById(Integer comId) {
        return commentDAO.getCommentById(comId);
    }

    @Override
    public Integer insertComment(CommentRequest commentRequest) {
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
