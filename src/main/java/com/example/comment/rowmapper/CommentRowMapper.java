package com.example.comment.rowmapper;

import com.example.comment.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();

        comment.setComId(rs.getInt("com_id"));
        comment.setActId(rs.getInt("act_id"));
        comment.setMemId(rs.getInt("mem_id"));
        comment.setComReplyId(rs.getInt("com_reply_id"));
        comment.setComContent(rs.getString("com_content"));
        comment.setComTime(rs.getTimestamp("com_time"));
        comment.setComStatus(rs.getInt("com_status"));

        return comment;
    }
}
