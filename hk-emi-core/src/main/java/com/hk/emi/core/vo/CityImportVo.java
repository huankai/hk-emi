package com.hk.emi.core.vo;

import com.hk.commons.poi.excel.annotations.ReadExcel;
import lombok.Data;

import java.io.Serializable;

/**
 * 城市导入Vo
 *
 * @author: huangkai
 * @date 2018-04-13 16:34
 */
@Data
public class CityImportVo implements Serializable {

    /**
     * 上级名称
     */
    @ReadExcel(start = 0)
    private String parentName;

    /**
     * 行政代码
     */
    @ReadExcel(start = 1)
    private String code;

    /**
     * 全称
     */
    @ReadExcel(start = 2)
    private String fullName;

    /**
     * 简称
     */
    @ReadExcel(start = 3)
    private String shortName;

    /**
     * 邮编
     */
    @ReadExcel(start = 4)
    private String postOffice;

    /**
     * 描述
     */
    @ReadExcel(start = 5)
    private String description;
}
