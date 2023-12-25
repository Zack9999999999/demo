package com.example.commentreport.dto;

import javax.validation.constraints.NotNull;

public class CommentReportStatus {
    @NotNull
    private byte repStatus;

    public byte getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(byte repStatus) {
        this.repStatus = repStatus;
    }
}
