package com.example.commentreport.model;

import com.example.comment.model.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "activity_comment_report")
public class CommentReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rep_id")
	private Integer repId;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "com_id", referencedColumnName = "com_id")
	private Comment comment;
//	private Integer com_id;

	@Column(name = "mem_id") //有table後再改成ManyToOne
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
	private Date repTime;

	public Integer getRepId() {
		return repId;
	}

	public void setRepId(Integer repId) {
		this.repId = repId;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getRepTitle() {
		return repTitle;
	}

	public void setRepTitle(String repTitle) {
		this.repTitle = repTitle;
	}

	public String getRepContent() {
		return repContent;
	}

	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}

	public byte[] getRepPic() {
		return repPic;
	}

	public void setRepPic(byte[] repPic) {
		this.repPic = repPic;
	}

	public byte getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(byte repStatus) {
		this.repStatus = repStatus;
	}

	public Date getRepTime() {
		return repTime;
	}

	public void setRepTime(Date repTime) {
		this.repTime = repTime;
	}

}
