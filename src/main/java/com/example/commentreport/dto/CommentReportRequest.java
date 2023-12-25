package com.example.commentreport.dto;

import com.example.commentreport.constant.CommentReportRepTitle;

import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CommentReportRequest {

    @NotNull
    private Integer comId;
    @NotNull
    private Integer memId;
    @NotNull
//  @Convert(converter = CommentReportRepTitleConverter.class)
    private CommentReportRepTitle repTitle;
    @NotBlank
    private String repContent;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public CommentReportRepTitle getRepTitle() {
        return repTitle;
    }

    public void setRepTitle(CommentReportRepTitle repTitle) {
        this.repTitle = repTitle;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

}
