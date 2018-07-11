package com.hk.emi.test;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: kevin
 * @date 2018-04-17 09:56
 */
@Entity
@Table(name = "pub_physical_value")
public class PubPhysicalValue {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @Column(name = "physical_value_id")
    private String physicalValueId;

    @Column(name = "physical_quality_id")
    private String physicalQualityId;

    @Column(name = "grade")
    private String grade;

    @Column(name = "sex")
    private String sex;

    @Column(name = "max_value")
    private Double maxValue;

    @Column(name = "min_value")
    private Double minValue;

    @Column(name = "score")
    private Double score;

    @Column(name = "level")
    private String level;

    @Column(name = "remark")
    private String remark;

    @Column(name = "last_up_time")
    private Date lastUpDate;

    @Column(name = "create_time")
    private Date createTime;

    public String getPhysicalValueId() {
        return physicalValueId;
    }

    public void setPhysicalValueId(String physicalValueId) {
        this.physicalValueId = physicalValueId;
    }

    public String getPhysicalQualityId() {
        return physicalQualityId;
    }

    public void setPhysicalQualityId(String physicalQualityId) {
        this.physicalQualityId = physicalQualityId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLastUpDate() {
        return lastUpDate;
    }

    public void setLastUpDate(Date lastUpDate) {
        this.lastUpDate = lastUpDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
