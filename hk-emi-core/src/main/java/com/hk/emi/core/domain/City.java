package com.hk.emi.core.domain;

import com.hk.commons.util.ByteConstants;
import com.hk.core.data.jpa.domain.AbstractTreePersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
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
public class City extends AbstractTreePersistable<City> {

    /**
     * 行政代码
     */
    @Column(name = "code")
    private String code;

    /**
     * <p>
     * 城市类型:
     * 1:国家,
     * 2:省或直辖市,
     * 3:市,
     * 4:区或县,
     * 5:镇,
     * 6:村
     * </p>
     *
     * @see com.hk.emi.core.domain.City.CityType
     */
    @Column(name = "city_type")
    private Byte cityType;

    /**
     * 全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 简名
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 英文名
     */
    @Column(name = "english_name")
    private String englishName;

    /**
     * 邮编
     */
    @Column(name = "post_office")
    private String postOffice;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    public enum CityType {

        COUNTRY(ByteConstants.ONE, "国家"),

        PROVINCE(ByteConstants.TWO, "省"),

        CITY(ByteConstants.THREE, "市"),

        AREA(ByteConstants.FOUR, "区或县"),

        TOWN(ByteConstants.FIVE, "镇"),

        VILLAGE(ByteConstants.SIX, "村");

        private Byte cityType;

        private String value;

        CityType(Byte cityType, String value) {
            this.cityType = cityType;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public byte getCityType() {
            return cityType;
        }
    }

    public String getCityTypeChinease() {
        CityType[] values = CityType.values();
        Byte cityType = getCityType();
        for (CityType type : values) {
            if (type.cityType.equals(cityType)) {
                return type.value;
            }
        }
        throw new IllegalStateException("Parameters that can not be identified.paramter value :" + cityType);
    }

}
