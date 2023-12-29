package com.example.comment.dao;

import com.example.comment.dto.CommentQueryParams;
import com.example.comment.dto.CommentRequest;
import com.example.comment.model.CommentVO;

import java.util.List;

public interface ICommentDAO {

    List<CommentVO> getComments(CommentQueryParams commentQueryParams);
    CommentVO getCommentById(Integer comId);
    List<CommentVO> insertComment(CommentRequest commentRequest, CommentQueryParams commentQueryParams);
    void updateComment(Integer comId, CommentRequest commentRequest);
    void deleteComment(Integer comId);

}
