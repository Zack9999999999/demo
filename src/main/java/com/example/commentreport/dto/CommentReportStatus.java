package com.example.commentreport.dto;

import javax.validation.constraints.NotNull;

public class CommentReportStatus {
    @NotNull
    private Byte repStatus;

    public Byte getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(Byte repStatus) {
        this.repStatus = repStatus;
    }

    @Override
    public String toString() {
        return "CommentReportStatus{" +
                "repStatus=" + repStatus +
                '}';
    }
}
