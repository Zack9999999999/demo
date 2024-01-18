package com.example.details.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class ActDTO {

    private Integer actId;

    private Integer memId;


    private String actName;

    private Date actStartTime;

    private Date actEndTime;

    private String actLoc;

    private String actDescr;

    private Integer actUpper;

    private Integer actCount;

    private Byte actStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime actCrTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regEndTime;

    private byte[] actPic;

    private Double actTotRating;

    private Integer actRateCount;

    private Integer actFollowCount;

    private BigDecimal lat;

    private BigDecimal lon;

    private byte[] memPic;

    public ActDTO(){

    }

    public ActDTO(Integer actId, Integer memId, String actName, Date actStartTime, Date actEndTime, String actLoc, String actDescr, Integer actUpper, Integer actCount, Byte actStatus, LocalDateTime actCrTime, LocalDateTime regStartTime, LocalDateTime regEndTime, byte[] actPic, Double actTotRating, Integer actRateCount, Integer actFollowCount, BigDecimal lat, BigDecimal lon, byte[] memPic) {
        this.actId = actId;
        this.memId = memId;
        this.actName = actName;
        this.actStartTime = actStartTime;
        this.actEndTime = actEndTime;
        this.actLoc = actLoc;
        this.actDescr = actDescr;
        this.actUpper = actUpper;
        this.actCount = actCount;
        this.actStatus = actStatus;
        this.actCrTime = actCrTime;
        this.regStartTime = regStartTime;
        this.regEndTime = regEndTime;
        this.actPic = actPic;
        this.actTotRating = actTotRating;
        this.actRateCount = actRateCount;
        this.actFollowCount = actFollowCount;
        this.lat = lat;
        this.lon = lon;
        this.memPic = memPic;
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

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Date getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(Date actStartTime) {
        this.actStartTime = actStartTime;
    }

    public Date getActEndTime() {
        return actEndTime;
    }

    public void setActEndTime(Date actEndTime) {
        this.actEndTime = actEndTime;
    }

    public String getActLoc() {
        return actLoc;
    }

    public void setActLoc(String actLoc) {
        this.actLoc = actLoc;
    }

    public String getActDescr() {
        return actDescr;
    }

    public void setActDescr(String actDescr) {
        this.actDescr = actDescr;
    }

    public Integer getActUpper() {
        return actUpper;
    }

    public void setActUpper(Integer actUpper) {
        this.actUpper = actUpper;
    }

    public Integer getActCount() {
        return actCount;
    }

    public void setActCount(Integer actCount) {
        this.actCount = actCount;
    }

    public Byte getActStatus() {
        return actStatus;
    }

    public void setActStatus(Byte actStatus) {
        this.actStatus = actStatus;
    }

    public LocalDateTime getActCrTime() {
        return actCrTime;
    }

    public void setActCrTime(LocalDateTime actCrTime) {
        this.actCrTime = actCrTime;
    }

    public LocalDateTime getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(LocalDateTime regStartTime) {
        this.regStartTime = regStartTime;
    }

    public LocalDateTime getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(LocalDateTime regEndTime) {
        this.regEndTime = regEndTime;
    }

    public byte[] getActPic() {
        return actPic;
    }

    public void setActPic(byte[] actPic) {
        this.actPic = actPic;
    }

    public Double getActTotRating() {
        return actTotRating;
    }

    public void setActTotRating(Double actTotRating) {
        this.actTotRating = actTotRating;
    }

    public Integer getActRateCount() {
        return actRateCount;
    }

    public void setActRateCount(Integer actRateCount) {
        this.actRateCount = actRateCount;
    }

    public Integer getActFollowCount() {
        return actFollowCount;
    }

    public void setActFollowCount(Integer actFollowCount) {
        this.actFollowCount = actFollowCount;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public byte[] getMemPic() {
        return memPic;
    }

    public void setMemPic(byte[] memPic) {
        this.memPic = memPic;
    }
}
