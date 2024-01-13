package com.example.actreg.dto;

import javax.validation.constraints.NotNull;

public class ActRegStatus {
    @NotNull
    private Integer actId;
    @NotNull
    private Byte regStatus;

    public Byte getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(Byte regStatus) {
        this.regStatus = regStatus;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }
}
