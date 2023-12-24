package com.example.actfollowed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity_followed")
public class ActFollowed {
    @Id
    @Column(name = "act_id")
    private Integer actId;
    @Column(name = "mem_id")
    private Integer memId;

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }
}
