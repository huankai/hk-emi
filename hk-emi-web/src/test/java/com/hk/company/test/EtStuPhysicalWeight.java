package com.hk.company.test;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: huangkai
 * @date 2018-04-20 17:22
 */
@Entity
@Table(name = "et_stu_physical_weight")
public class EtStuPhysicalWeight {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "student_id")
    private String studentId;

    /**
     * 姓名
     */
    @Column(name = "stu_name")
    private String stuName;
    /**
     * 学年ID
     */
    @Column(name = "schoolyear_id")
    private String schoolYearId;

    /**
     * 学年
     */
    @Column(name = "schoolyear_name")
    private String schoolyearName;

    /**
     * 学期ID
     */
    @Column(name = "semester_id")
    private String semesterId;

    /**
     * 学期
     */
    @Column(name = "semester_name")
    private String semesterName;

    /**
     * 年级ID
     */
    @Column(name = "grade_id")
    private String gradeId;

    /**
     * 年级
     */
    @Column(name = "grade_name")
    private String gradeName;

    /**
     * 班级ID
     */
    @Column(name = "class_id")
    private String classId;

    /**
     * 班级
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 总分数
     */
    @Column(name = "score")
    private Double score;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @Column(name = "last_up_time")
    private Date lastUpTime = new Date();

    @Column(name = "create_time")
    private Date createTime = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(String schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public String getSchoolyearName() {
        return schoolyearName;
    }

    public void setSchoolyearName(String schoolyearName) {
        this.schoolyearName = schoolyearName;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
