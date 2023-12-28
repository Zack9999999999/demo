package com.example.comment.service;

import com.example.comment.dto.CommentRequest;
import com.example.comment.model.CommentVO;

import java.util.List;

public interface ICommentService {
    List<CommentVO> getComments();
    CommentVO getCommentById(Integer comId);
    List<CommentVO> insertComment(CommentRequest commentRequest);
    void updateComment(Integer comId, CommentRequest commentRequest);
    void deleteComment(Integer comId);
}
