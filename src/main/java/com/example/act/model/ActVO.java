package com.example.act.model;
import com.example.actreg.model.ActRegVO;
import com.example.commentreport.model.CommentReportVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "activity")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private Date actStartTime;

    @Column(name = "act_end_time",nullable = false)
    private Date actEndTime;

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

    @OneToMany(mappedBy = "act", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ActRegVO> actReg;


}
