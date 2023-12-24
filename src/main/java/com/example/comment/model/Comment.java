package com.example.comment.model;

import com.example.commentreport.model.CommentReport;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "activity_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Integer comId;
    @Column(name = "act_id")
    private Integer actId;
    @Column(name = "mem_id")
    private Integer memId;
    @Column(name = "com_reply_id")
    private Integer comReplyId;
    @Column(name = "com_content")
    private String comContent;
    @Column(name = "com_time")
    private Timestamp comTime;
    @Column(name = "com_status")
    private Integer comStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private Set<CommentReport> commentReport;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

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

    public Integer getComReplyId() {
        return comReplyId;
    }

    public void setComReplyId(Integer comReplyId) {
        this.comReplyId = comReplyId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Timestamp getComTime() {
        return comTime;
    }

    public void setComTime(Timestamp comTime) {
        this.comTime = comTime;
    }

    public Integer getComStatus() {
        return comStatus;
    }

    public void setComStatus(Integer comStatus) {
        this.comStatus = comStatus;
    }

    public Set<CommentReport> getCommentReport() {
        return commentReport;
    }

    public void setCommentReport(Set<CommentReport> commentReport) {
        this.commentReport = commentReport;
    }

}
