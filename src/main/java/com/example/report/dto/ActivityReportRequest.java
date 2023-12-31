package com.example.report.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActivityReportRequest {

    @NotNull
    private Integer actId;

    @NotNull
    private Integer memId;

    private Integer empId;

    @NotNull
    private String repTitle;

    @NotNull
    private String repContent;

    private byte[] repPic;

    private Byte repStatus;

//    private Date repTime;
}
