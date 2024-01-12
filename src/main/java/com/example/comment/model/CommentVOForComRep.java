package com.example.comment.model;

import com.example.commentreport.model.CommentReportVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "activity_comment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVOForComRep implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private Date comTime;
    @Column(name = "com_status")
    private Byte comStatus;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CommentReportVO> commentReport;

}
