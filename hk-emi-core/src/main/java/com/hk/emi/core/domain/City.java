/**
 * 
 */
package com.hk.emi.core.domain;

import com.hk.emi.core.domain.ModelHolder.CityBase;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 城市表
 * 
 * @author huangkai
 * @date 2017年12月24日下午8:14:32
 */
@Data
@Entity
@Table(name = "sys_city")
public class City extends CityBase {

}
