package com.hk.emi.core.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 字典
 * 
 * @author: huangkai
 * @date 2017-11-29 16:27
 */
@Entity
@Table(name = "sys_base_code")
@Data
public class BaseCode extends ModelHolder.BaseCodeBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2836976613651239830L;

}
