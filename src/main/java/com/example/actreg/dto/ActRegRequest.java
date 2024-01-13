package com.example.actreg.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ActRegRequest {

    private Integer memId;
    @NotNull
    private Integer actId;
    @NotNull
    private Integer regTotal;

    private Byte regStatus = 2; //2為審核 3為成功報名

    private Byte isActPart = 2;

    private Double actRating;
}
