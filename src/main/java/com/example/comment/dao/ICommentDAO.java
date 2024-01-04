package com.example.comment.dao;

import com.example.comment.dto.CommentQueryParams;
import com.example.comment.dto.CommentRequest;
import com.example.comment.model.CommentVO;

import java.util.List;

public interface ICommentDAO {

    Integer countComments();

    List<CommentVO> getComments(CommentQueryParams commentQueryParams);
    CommentVO getCommentById(Integer comId);
    Integer insertComment(CommentRequest commentRequest);
    void updateComment(Integer comId, CommentRequest commentRequest);
    void deleteComment(Integer comId);

}
