package com.example.report.model;

import com.example.act.model.ActVO;
import com.example.report.constant.ReportTitle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "activity_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityReportVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_id")
    private Integer repId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_id", referencedColumnName = "act_id")
    private ActVO act;

    @Column(name = "mem_id")
    private Integer memId;

    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "rep_title")
    @Enumerated(EnumType.STRING)
    private ReportTitle repTitle;

    @Column(name = "rep_content")
    private String repContent;

    @Column(name = "rep_pic", columnDefinition = "longblob")
    private byte[] repPic;

    @Column(name = "rep_status")
    private Byte repStatus = 1;

    @Column(name = "rep_time")
    private Date repTime;

}