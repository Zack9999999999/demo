package com.example.actfollowed.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "activity_followed")
@IdClass(ActFollowed.class)
public class ActFollowedVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "act_id")
    private Integer actId;

    @Id
    @Column(name = "mem_id")
    private Integer memId;

    @Column(name = "fol_status")
    private Byte folStatus;

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

    public Byte getFolStatus() {
        return folStatus;
    }

    public void setFolStatus(Byte folStatus) {
        this.folStatus = folStatus;
    }

    @Override
    public String toString() {
        return "ActFollowedVO{" +
                "actId=" + actId +
                ", memId=" + memId +
                ", folStatus=" + folStatus +
                '}';
    }
}
