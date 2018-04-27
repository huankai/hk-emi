package com.hk.pms.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2018-04-12 11:42
 */
@Data
@Entity
@Table(name = "sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends ModelHolder.SysUserBase {
}
