package com.hk.pms.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: huangkai
 * @date 2018-04-12 16:35
 */
@Data
@Entity
@Table(name = "sys_user_third")
@EqualsAndHashCode(callSuper = true)
public class SysUserThird extends ModelHolder.SysUserThirdBase {
}
