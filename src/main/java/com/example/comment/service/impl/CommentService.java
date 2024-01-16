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

import java.util.Collections;
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
    @Transactional
    public List<CommentVO> getComments(CommentQueryParams commentQueryParams) {
        if (commentQueryParams == null) {

        }
        StringBuilder redisKey = new StringBuilder()
                .append("comment")
                .append(":")
                .append("actId")
                .append(":")
                .append(commentQueryParams.getActId()
                        .toString());
        //redis查詢
        List<CommentVO> commentsCache = getCommentsCache(commentQueryParams);

        //Redis沒有 就去DB查
        if (commentsCache == null || commentsCache.isEmpty()) {
            List<CommentVO> comments = commentDAO.getComments(commentQueryParams);
            //反轉redis 變成新至舊
            Collections.reverse(comments);
            //查完同時存入Redis
            redisTemplate.opsForList().rightPushAll(redisKey.toString(), comments);

            return getCommentsCache(commentQueryParams);
        }

        //redis改變排序
        if (commentQueryParams.getSort().equals("DESC")) {
            Collections.reverse(commentsCache);
        }
        //有的話就直接return Redis
        return commentsCache;
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

        //為了可以call getComments而new
        CommentQueryParams commentQueryParams = new CommentQueryParams();

        commentDAO.getComments(commentQueryParams);
    }

    //存進Redis
    public void insertCommentCache(CommentVO commentById) {

        StringBuilder redisKey = new StringBuilder()
                .append("comment")
                .append(":")
                .append("actId")
                .append(":")
                .append(commentById.getActId()
                        .toString());

        redisTemplate.opsForList().leftPush(redisKey.toString(), commentById);
    }

    //查詢Redis
    public List<CommentVO> getCommentsCache(CommentQueryParams commentQueryParams) {
        StringBuilder redisKey = new StringBuilder()
                .append("comment")
                .append(":")
                .append("actId")
                .append(":")
                .append(commentQueryParams.getActId()
                        .toString());

        return (List<CommentVO>) redisTemplate
                .opsForList()
                .range(redisKey.toString(), 0, commentQueryParams.getLimit() - 1);
    }

}
