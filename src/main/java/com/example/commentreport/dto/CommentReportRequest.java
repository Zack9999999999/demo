package com.example.commentreport.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CommentReportRequest {

    @NotNull
    private Integer comId;
    @NotNull
    private Integer memId;
    @NotBlank
    private String repTitle;
    @NotBlank
    private String repContent;
    private byte[] repPic; //這要存圖片, 應該還需要做處理

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

    public String getRepTitle() {
        return repTitle;
    }

    public void setRepTitle(String repTitle) {
        this.repTitle = repTitle;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

    public byte[] getRepPic() {
        return repPic;
    }

    public void setRepPic(byte[] repPic) {
        this.repPic = repPic;
    }

}
