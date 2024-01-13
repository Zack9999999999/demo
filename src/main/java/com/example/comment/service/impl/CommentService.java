package com.example.comment.service.impl;

import com.example.comment.dao.ICommentDAO;
import com.example.comment.dto.CommentQueryParams;
import com.example.comment.dto.CommentRequest;
import com.example.comment.dto.CommentStatus;
import com.example.comment.service.ICommentService;
import com.example.comment.model.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class CommentService implements ICommentService {

    @Autowired
    private ICommentDAO commentDAO;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer countComments(CommentQueryParams commentQueryParams) {

        return commentDAO.countComments(commentQueryParams);
    }

    @Override
    public List<CommentVO> getComments(CommentQueryParams commentQueryParams) {

//        String redisKey = commentQueryParams.getActId().toString();

//        List<CommentVO> comment = (List<CommentVO>) redisTemplate.opsForValue().get(redisKey);

        //Redis沒有 就去DB查
//        if (comment == null || comment.isEmpty()) {
//            List<CommentVO> comments = commentDAO.getComments(commentQueryParams);
//
//            //查完同時存入Redis
//            redisTemplate.opsForValue().set(redisKey, comments);
//
//            return comments;
//        }

        //有的話就直接return Redis
//        return comment;

        return commentDAO.getComments(commentQueryParams);
    }

    @Override
    public CommentVO getCommentById(Integer comId) {
        return commentDAO.getCommentById(comId);
    }

    @Override
    @Transactional
    public Integer insertComment(CommentRequest commentRequest) {

        Integer commentId = commentDAO.insertComment(commentRequest);

        //存Redis
//        insertCommentCache(commentRequest);

        return commentId;
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

    //存進Redis
    @Override
    public void insertCommentCache(CommentRequest commentRequest) {

        String redisKey = commentRequest.getActId().toString();

        redisTemplate.opsForValue().set(redisKey, commentRequest);
    }

    //新增跟刪除要更新redis
}
