package com.hk.pms.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * App Entity
 *
 * @author: huangkai
 * @date 2018-04-12 11:24
 */
@Entity
@Table(name = "sys_app")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysApp extends ModelHolder.SysAppBase {


}
