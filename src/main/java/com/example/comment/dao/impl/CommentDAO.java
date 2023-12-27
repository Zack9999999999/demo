package com.example.comment.dao.impl;

import com.example.comment.dao.ICommentDAO;
import com.example.comment.dto.CommentRequest;
import com.example.comment.model.CommentVO;
import com.example.comment.rowmapper.CommentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommentDAO implements ICommentDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<CommentVO> getComments(String actId) {
        String sql = "SELECT com_id, act_id, mem_id, com_reply_id, com_content, com_time, com_status " +
                "FROM activity_comment WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        if(actId != null){
            sql = sql + " AND act_id = :actId";
            map.put("actId", actId);
        }

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
    @Transactional
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

        int comId = keyHolder.getKey().intValue();

        return comId;
    }

    @Override
    public void updateComment(Integer comId, CommentRequest commentRequest) {

        String sql = "UPDATE activity_comment SET com_content = :comContent WHERE comId = :comId";

        Map<String, Object> map = new HashMap<>();
        map.put("comContent", commentRequest.getComContent());
        map.put("comId", comId);

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
