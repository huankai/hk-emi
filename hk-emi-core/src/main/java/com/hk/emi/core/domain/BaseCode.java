package com.hk.emi.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典
 * 
 * @author: huangkai
 * @date 2017-11-29 16:27
 */
@Entity
@Table(name = "sys_base_code")
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseCode extends ModelHolder.BaseCodeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2836976613651239830L;

}
