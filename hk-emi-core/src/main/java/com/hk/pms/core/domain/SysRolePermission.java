package com.hk.pms.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2018-04-12 16:36
 */
@Data
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermission extends ModelHolder.SysRolePermissionBase {
}