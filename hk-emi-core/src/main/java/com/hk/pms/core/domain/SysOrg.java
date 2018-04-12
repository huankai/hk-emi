package com.hk.pms.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2018-04-12 11:41
 */
@Entity
@Table(name = "sys_org")
@Data
public class SysOrg extends ModelHolder.SysOrgBase {
}
