package com.example.actreg.model;

import com.example.act.model.ActVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActRegVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_reg_id")
    private Integer actRegId;

    @Column(name = "mem_id")
    private Integer memId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_id", referencedColumnName = "act_id")
    private ActVO act;

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

    @Column(name = "reg_reason")
    private String regReason;

}
