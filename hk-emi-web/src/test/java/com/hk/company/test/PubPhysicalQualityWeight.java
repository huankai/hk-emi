package com.hk.company.test;

import com.hk.commons.poi.excel.annotations.ReadExcel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: huangkai
 * @date 2018-04-20 10:44
 */

@Entity
@Table(name = "pub_physical_quality_weight")
public class PubPhysicalQualityWeight {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    /**
     * 年级编号
     */
    @Column(name = "grade")
    @ReadExcel(start = 0)
    private String grade;

    @ReadExcel(start = 1)
    @Column(name = "grade_name")
    private String gradeName;

    /**
     * 身体素质Id
     */
    @Column(name = "physical_quality_id")
    private String physicalQualityId;

    @Transient
    @ReadExcel(start = 2)
    private String physicalTitle;

    /**
     * 权重值
     */
    @Column(name = "weight")
    @ReadExcel(start = 3)
    private Integer weight;

    @Column(name = "last_up_time")
    private Date lastUpTime;

    @Column(name = "create_time")
    private Date createTime;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Date getLastUpTime() {
        return lastUpTime;
    }

    public void setLastUpTime(Date lastUpTime) {
        this.lastUpTime = lastUpTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhysicalQualityId() {
        return physicalQualityId;
    }

    public void setPhysicalQualityId(String physicalQualityId) {
        this.physicalQualityId = physicalQualityId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPhysicalTitle() {
        return physicalTitle;
    }

    public void setPhysicalTitle(String physicalTitle) {
        this.physicalTitle = physicalTitle;
    }
}
