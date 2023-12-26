package com.example.comment.rowmapper;

import com.example.comment.model.CommentVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<CommentVO> {
    @Override
    public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommentVO comment = new CommentVO();

        comment.setComId(rs.getInt("com_id"));
        comment.setActId(rs.getInt("act_id"));
        comment.setMemId(rs.getInt("mem_id"));
        comment.setComReplyId(rs.getInt("com_reply_id"));
        comment.setComContent(rs.getString("com_content"));
        comment.setComTime(rs.getTimestamp("com_time"));
        comment.setComStatus(rs.getByte("com_status"));

        return comment;
    }
}
