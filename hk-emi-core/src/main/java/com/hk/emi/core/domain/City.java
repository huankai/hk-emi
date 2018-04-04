/**
 * 
 */
package com.hk.emi.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hk.emi.core.domain.ModelHolder.CityBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 城市表
 * 
 * @author huangkai
 * @date 2017年12月24日下午8:14:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_city")
@SuppressWarnings("serial")
public class City extends CityBase {

}
