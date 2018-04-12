package com.hk.pms.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2018-04-12 16:33
 */
@Data
@Entity
@Table(name = "sys_user_role")
public class SysUserRole extends ModelHolder.SysUserRoleBase {
}
