package com.hk.emi.vo;

import lombok.Data;

/**
 * @author huangkai
 * @date 2018-6-2 22:28
 */
@Data
public class City {

    private String id;

    /**
     * 行政代码
     */
    private String code;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 简名
     */
    private String shortName;

    /**
     * 英文名
     */
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
     */
    private String typeName;

    /**
     * 邮编
     */
    private String postOffice;
}
