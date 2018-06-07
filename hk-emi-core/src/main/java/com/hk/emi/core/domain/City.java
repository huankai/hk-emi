package com.hk.emi.core.domain;

import com.hk.commons.annotations.EnumDisplay;
import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.EnumDisplayUtils;
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
     * <pre>
     * 城市类型:
     *     1:国家,
     *     2:省或直辖市,
     *     3:市,
     *     4:区或县,
     *     5:镇,
     *     6:村
     * </pre>
     * @see com.hk.emi.core.domain.City.CityType
     */
    @Column(name = "city_type")
    private Byte cityType;

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

        private Byte cityType;

        CityType(Byte cityType) {
            this.cityType = cityType;
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
                return EnumDisplayUtils.getDisplayText(type.name(), CityType.class);
            }
        }
        throw new IllegalStateException("Parameters that can not be identified.paramter value :" + cityType);
    }

}
