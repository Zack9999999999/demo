package com.example.comment.dao;

import com.example.comment.dto.CommentRequest;
import com.example.comment.model.Comment;

import java.util.List;

public interface CommentDAO {

    List<Comment> getComments();
    Comment getCommentById(Integer comId);
    Integer insertComment(CommentRequest commentRequest);
    void updateComment(Integer comId, CommentRequest commentRequest);
    void deleteComment(Integer comId);

}
