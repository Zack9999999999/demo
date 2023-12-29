package com.example.act.model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "activity")
@Data
public class ActVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_id")
    private Integer actId;

    @Column(name = "mem_id",nullable = false)
    private Integer memId;

    @Column(name = "act_name",nullable = false)
    private String actName;

    @Column(name = "act_start_time",nullable = false)
    private LocalDateTime actStartTime;

    @Column(name = "act_end_time",nullable = false)
    private LocalDateTime actEndTime;

    @Column(name = "act_loc",nullable = false)
    private String actLoc;

    @Column(name = "act_descr",nullable = false)
    private String actDescr;

    @Column(name = "act_upper",nullable = false)
    private Integer actUpper;

    @Column(name = "act_count")
    private Integer actCount;

    @Column(name = "act_status",nullable = false)
    private Byte actStatus;

    @Column(name = "act_cr_time",nullable = false)
    private LocalDateTime actCrTime;

    @Column(name = "reg_start_time",nullable = false)
    private LocalDateTime regStartTime;

    @Column(name = "reg_end_time",nullable = false)
    private LocalDateTime regEndTime;

    @Column(name = "act_pic")
    private byte[] actPic;

    @Column(name = "act_tot_rating")
    private Double actTotRating;

    @Column(name = "act_rate_count")
    private Integer actRateCount;

    @Column(name = "act_follow_count")
    private Integer actFollowCount;

    @Column(name = "lat")
    private BigDecimal lat;

    @Column(name = "lon")
    private BigDecimal lon;

}
