package com.example.comment.dao.impl;

import com.example.comment.dao.ICommentDAO;
import com.example.comment.dto.CommentQueryParams;
import com.example.comment.dto.CommentRequest;
import com.example.comment.model.CommentVO;
import com.example.comment.rowmapper.CommentRowMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CommentDAO implements ICommentDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Integer countComments(CommentQueryParams commentQueryParams) {
        String sql = "SELECT count(*) FROM activity_comment WHERE com_status = 1";

        Map<String, Object> map = new HashMap<>();

        sql = sql + " AND act_id = :actId";
        map.put("actId", commentQueryParams.getActId());

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
//    @Cacheable(value = "commentsCache", key = "'comments'")
    public List<CommentVO> getComments(CommentQueryParams commentQueryParams) {
        String sql = "SELECT com_id, act_id, mem_id, com_reply_id, com_content, com_time, com_status " +
                "FROM activity_comment WHERE 1=1";

        //會員id要改成顯示會員名稱 即顯示會員大頭貼

        Map<String, Object> map = new HashMap<>();

        sql = sql + " AND act_id = :actId";
        map.put("actId", commentQueryParams.getActId());

        //狀態不等於1的話代表被檢舉成功所以不顯示
        sql = sql + " AND com_status = 1";

        //排序
        sql = sql + " ORDER BY " + commentQueryParams.getOrderBy() + " " + commentQueryParams.getSort();

        //簡易分頁(查看更多)
        sql = sql + " LIMIT :limit";
        map.put("limit", commentQueryParams.getLimit());

        List<CommentVO> commentList = namedParameterJdbcTemplate.query(sql, map, new CommentRowMapper());

        return commentList;
    }

    @Override
    public CommentVO getCommentById(Integer comId) {
        String sql = "SELECT com_id, act_id, mem_id, com_reply_id, com_content, com_time, com_status " +
                "FROM activity_comment WHERE com_id = :comId";

        Map<String, Object> map = new HashMap<>();
        map.put("comId", comId);

        List<CommentVO> commentList = namedParameterJdbcTemplate.query(sql, map, new CommentRowMapper());

        if (commentList.size() > 0) {
            return commentList.get(0);
        } else {
            return null;
        }
    }

    @Override
//    @CachePut(value = "commentsCache", key = "'comments'")
    public Integer insertComment(CommentRequest commentRequest) {

        String sql = "INSERT INTO activity_comment(act_id, mem_id, com_reply_id, com_content, com_time) " +
                "VALUES(:actId, :memId, :comReplyId, :comContent, :comTime)";

        Map<String, Object> map = new HashMap<>();
        map.put("actId", commentRequest.getActId());
        map.put("memId", commentRequest.getMemId());
        map.put("comReplyId", commentRequest.getComReplyId());
        map.put("comContent", commentRequest.getComContent());
        map.put("comTime", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        Integer commentId = keyHolder.getKey().intValue();

        return commentId;
    }

    @Override
    public void updateComment(Integer comId, CommentRequest commentRequest) {

        String sql = "UPDATE activity_comment SET com_content = :comContent WHERE comId = :comId";

        Map<String, Object> map = new HashMap<>();
        map.put("comContent", commentRequest.getComContent());
        map.put("comId", comId);
        //還要增加修改後的時間 並且畫面顯示"編輯後"
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteComment(Integer comId) {

        String sql = "DELETE FROM activity_comment WHERE com_id = :comId";

        Map<String, Object> map = new HashMap<>();
        map.put("comId", comId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
