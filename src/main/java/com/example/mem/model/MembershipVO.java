package com.example.mem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membership")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MembershipVO implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id", insertable = false, updatable = false)
    private Integer memId;

    @Column(name = "mem_acc")
    private String memAcc;

    @Column(name = "mem_email")
    private String memEmail;

    @Column(name = "mem_pwd")
    private String memPwd;

    @Column(name = "mem_name")
    private String memName;

    @Column(name = "mem_gender")
    private Byte memGender;

    @Column(name = "mem_birthdate")
    private Date memBirthdate;

    @Column(name = "mem_username")
    private String memUsername;

    @Column(name = "mem_pic", columnDefinition = "longblob")
    private byte[] memPic;

    @Column(name = "mem_intro")
    private String memIntro;

    @Column(name = "mem_phone")
    private String memPhone;

    @Column(name = "block_start_time")
    private Timestamp blockStartTime;

    @Column(name = "block_end_time")
    private Timestamp blockEndTime;

    @Column(name = "is_acc_ena")
    private Byte isAccEna;

    @Column(name = "is_part_ena")
    private Byte isPartEna;

    @Column(name = "is_host_ena")
    private Byte isHostEna;

    @Column(name = "is_rent_ena")
    private Byte isRentEna;

    @Column(name = "is_msg_ena")
    private Byte isMsgEna;

    @Column(name = "mem_cr_time")
    private Timestamp memCrTime;

    @Column(name = "mem_login_time")
    private Timestamp memLoginTime;

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public String getMemAcc() {
        return memAcc;
    }

    public void setMemAcc(String memAcc) {
        this.memAcc = memAcc;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public Byte getMemGender() {
        return memGender;
    }

    public void setMemGender(Byte memGender) {
        this.memGender = memGender;
    }

    public Date getMemBirthdate() {
        return memBirthdate;
    }

    public void setMemBirthdate(Date memBirthdate) {
        this.memBirthdate = memBirthdate;
    }

    public String getMemUsername() {
        return memUsername;
    }

    public void setMemUsername(String memUsername) {
        this.memUsername = memUsername;
    }

    public byte[] getMemPic() {
        return memPic;
    }

    public void setMemPic(byte[] memPic) {
        this.memPic = memPic;
    }

    public String getMemIntro() {
        return memIntro;
    }

    public void setMemIntro(String memIntro) {
        this.memIntro = memIntro;
    }

    public String getMemPhone() {
        return memPhone;
    }

    public void setMemPhone(String memPhone) {
        this.memPhone = memPhone;
    }

    public Timestamp getBlockStartTime() {
        return blockStartTime;
    }

    public void setBlockStartTime(Timestamp blockStartTime) {
        this.blockStartTime = blockStartTime;
    }

    public Timestamp getBlockEndTime() {
        return blockEndTime;
    }

    public void setBlockEndTime(Timestamp blockEndTime) {
        this.blockEndTime = blockEndTime;
    }

    public Byte getIsAccEna() {
        return isAccEna;
    }

    public void setIsAccEna(Byte isAccEna) {
        this.isAccEna = isAccEna;
    }

    public Byte getIsPartEna() {
        return isPartEna;
    }

    public void setIsPartEna(Byte isPartEna) {
        this.isPartEna = isPartEna;
    }

    public Byte getIsHostEna() {
        return isHostEna;
    }

    public void setIsHostEna(Byte isHostEna) {
        this.isHostEna = isHostEna;
    }

    public Byte getIsRentEna() {
        return isRentEna;
    }

    public void setIsRentEna(Byte isRentEna) {
        this.isRentEna = isRentEna;
    }

    public Byte getIsMsgEna() {
        return isMsgEna;
    }

    public void setIsMsgEna(Byte isMsgEna) {
        this.isMsgEna = isMsgEna;
    }

    public Timestamp getMemCrTime() {
        return memCrTime;
    }

    public void setMemCrTime(Timestamp memCrTime) {
        this.memCrTime = memCrTime;
    }

    public Timestamp getMemLoginTime() {
        return memLoginTime;
    }

    public void setMemLoginTime(Timestamp memLoginTime) {
        this.memLoginTime = memLoginTime;
    }

}