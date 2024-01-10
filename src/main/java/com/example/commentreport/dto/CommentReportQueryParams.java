package com.example.commentreport.dto;

import com.example.commentreport.constant.CommentReportRepTitle;

import java.util.Date;

public class CommentReportQueryParams {
    private CommentReportRepTitle commentReportRepTitle;
    private Byte repStatus;
    private Integer page;
    private Integer size;

    public CommentReportRepTitle getCommentReportRepTitle() {
        return commentReportRepTitle;
    }

    public void setCommentReportRepTitle(CommentReportRepTitle commentReportRepTitle) {
        this.commentReportRepTitle = commentReportRepTitle;
    }

    public Byte getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(Byte repStatus) {
        this.repStatus = repStatus;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
