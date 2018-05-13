package com.hk.emi.core.domain;

import com.hk.commons.annotations.EnumDisplay;
import com.hk.commons.util.ByteConstants;
import com.hk.emi.core.domain.ModelHolder.CityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
public class City extends CityBase {

    public enum CityType {

        @EnumDisplay(value = "国家", order = 1)
        COUNTRY(ByteConstants.ONE),

        @EnumDisplay(value = "省", order = 2)
        PROVINCE(ByteConstants.TWO),

        @EnumDisplay(value = "市", order = 3)
        CITY(ByteConstants.THREE),

        @EnumDisplay(value = "区或县", order = 4)
        AREA(ByteConstants.FOUR),

        @EnumDisplay(value = "镇", order = 5)
        TOWN(ByteConstants.FIVE),

        @EnumDisplay(value = "村", order = 6)
        VILLAGE(ByteConstants.SIX);

        private byte cityType;

        CityType(byte cityType) {
            this.cityType = cityType;
        }

        public byte getCityType() {
            return cityType;
        }
    }

}
