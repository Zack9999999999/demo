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

        StringBuilder redisKey = new StringBuilder()
                .append("comment")
                .append(":")
                .append("actId")
                .append(":")
                .append(commentQueryParams.getActId()
                        .toString());

        List<CommentVO> comment = (List<CommentVO>) redisTemplate
                .opsForList()
                .range(redisKey.toString(), 0, -1);

//1.        commentQueryParams.setLimit();// 幾筆
//2.        if(comment.equals(comments))

        //Redis沒有 就去DB查
        if (comment == null || comment.isEmpty()) {
            List<CommentVO> comments = commentDAO.getComments(commentQueryParams);

            //查完同時存入Redis
            redisTemplate.opsForList().rightPushAll(redisKey.toString(), comments);

            return comments;
        }

        //有的話就直接return Redis
        return comment;

//        return commentDAO.getComments(commentQueryParams);
    }

    @Override
    public CommentVO getCommentById(Integer comId) {
        return commentDAO.getCommentById(comId);
    }

    @Override
    @Transactional
    public Integer insertComment(CommentRequest commentRequest) {

        Integer commentId = commentDAO.insertComment(commentRequest);

        CommentVO commentById = commentDAO.getCommentById(commentId);
        //存Redis
        insertCommentCache(commentById);

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
    public void insertCommentCache(CommentVO commentById) {

        StringBuilder redisKey = new StringBuilder()
                .append("comment")
                .append(":")
                .append("actId")
                .append(":")
                .append(commentById.getActId()
                        .toString());

        redisTemplate.opsForList().rightPush(redisKey.toString(), commentById);
    }

}
