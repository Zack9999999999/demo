package com.example.report.dto;

import com.example.report.constant.ReportTitle;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActivityReportRequest {

    @NotNull
    private Integer actId;

    private Integer memId;

    private Integer empId; //不需要

    @NotNull
    private ReportTitle repTitle;

    @NotNull
    private String repContent;

    private byte[] repPic;

    private Byte repStatus = 1;

//    private Date repTime;
}
