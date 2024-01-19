package com.example.actreg.dto;

public class ActRegQueryParams {

    private Byte regStatus;
    private Integer page;
    private Integer size;

    public Byte getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(Byte regStatus) {
        this.regStatus = regStatus;
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
