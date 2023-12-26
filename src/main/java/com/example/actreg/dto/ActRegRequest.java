package com.example.actreg.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ActRegRequest {
    @NotNull
    private Integer memId;
    @NotNull
    private Integer actId;
    @NotNull
    private Integer regTotal;

    private Byte regStatus;

    private Byte isActPart;

    private Double actRating;
}
