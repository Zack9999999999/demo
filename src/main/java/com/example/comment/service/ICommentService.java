package com.example.comment.service;

import com.example.comment.dto.CommentRequest;
import com.example.comment.model.CommentVO;

import java.util.List;

public interface ICommentService {
    List<CommentVO> getComments(String actId);
    CommentVO getCommentById(Integer comId);
    Integer insertComment(CommentRequest commentRequest);
    void updateComment(Integer comId, CommentRequest commentRequest);
    void deleteComment(Integer comId);
}
