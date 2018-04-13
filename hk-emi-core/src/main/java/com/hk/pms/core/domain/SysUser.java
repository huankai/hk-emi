package com.hk.pms.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2018-04-12 11:42
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser extends ModelHolder.SysUserBase {
}
