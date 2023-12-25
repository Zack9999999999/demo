package com.example.report.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_report")
@Data
public class ActivityReportVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rep_id")
	private Integer repId;
	@Column(name = "act_id")
	private Integer actId;
	@Column(name = "mem_id")
	private Integer memId;
	@Column(name = "emp_id")
	private Integer empId;
	@Column(name = "rep_title")
	private String repTitle;
	@Column(name = "rep_content")
	private String repContent;
	@Column(name = "rep_pic", columnDefinition = "longblob")
	private byte[] repPic;
	@Column(name = "rep_status")
	private byte repStatus;
	@Column(name = "rep_time")
	private Timestamp repTime;

}
