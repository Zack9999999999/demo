package com.example.commentreport.model;

import com.example.comment.model.CommentVO;
import com.example.commentreport.constant.CommentReportRepTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "activity_comment_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReportVO implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_id")
    private Integer repId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_id", referencedColumnName = "com_id")
    private CommentVO comment;

    @Column(name = "mem_id") //有table後再改成ManyToOne
    private Integer memId;

    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "rep_title")
    @Enumerated(EnumType.STRING)
    private CommentReportRepTitle repTitle;

    @Column(name = "rep_content")
    private String repContent;

    @Column(name = "rep_status")
    private Byte repStatus;

    @Column(name = "rep_time")
    private Date repTime;

}
