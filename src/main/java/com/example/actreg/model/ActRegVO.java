package com.example.actreg.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity_registration")
@Data
public class ActRegVO {

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
	private Date regTime;

	@Column(name = "reg_status")
	private Byte regStatus;

	@Column(name = "is_act_part")
	private Byte isActPart;

	@Column(name = "act_rating")
	private Double actRating;

}
