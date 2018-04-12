package com.hk.pms.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2018-04-12 11:48
 */
@Table(name = "sys_org_dept")
@Entity
@Data
public class SysOrgDept extends ModelHolder.SysOrgDeptBase {


}
