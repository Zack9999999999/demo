package com.example.actreg.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity_registration")
public class ActReg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "act_reg_id")
	private Integer actRegId;
	@Column(name = "mem_id")
	private Integer memId;
	@Column(name = "act_id")
	private Integer actId;
	@Column(name = "reg_total")
	private Integer regTotal;
	@Column(name = "reg_time")
	private Timestamp regTime;
	@Column(name = "reg_status")
	private byte regStatus;
	@Column(name = "is_act_part")
	private byte isActPart;
	@Column(name = "act_rating")
	private Double actRating;

	public Integer getActRegId() {
		return actRegId;
	}

	public void setActRegId(Integer actRegId) {
		this.actRegId = actRegId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public Integer getRegTotal() {
		return regTotal;
	}

	public void setRegTotal(Integer regTotal) {
		this.regTotal = regTotal;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public byte getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(byte regStatus) {
		this.regStatus = regStatus;
	}

	public byte getIsActPart() {
		return isActPart;
	}

	public void setIsActPart(byte isActPart) {
		this.isActPart = isActPart;
	}

	public Double getActRating() {
		return actRating;
	}

	public void setActRating(Double actRating) {
		this.actRating = actRating;
	}

}
