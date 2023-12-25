package com.example.actfollowed.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity_followed")
@Data
public class ActFollowedVO {

    @Id
    @Column(name = "act_id")
    private Integer actId;
    @Column(name = "mem_id")
    private Integer memId;

}
