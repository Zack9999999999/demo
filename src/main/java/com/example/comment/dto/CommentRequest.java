package com.example.comment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentRequest {

    @NotNull
    private Integer actId;
    @NotNull
    private Integer memId;

    private Integer comReplyId;
    @NotNull
    @NotBlank
    private String comContent;

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getComReplyId() {
        return comReplyId;
    }

    public void setComReplyId(Integer comReplyId) {
        this.comReplyId = comReplyId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

}
