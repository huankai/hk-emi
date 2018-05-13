package com.hk.emi.core.vo;

import com.hk.commons.poi.excel.annotations.ReadExcel;
import com.hk.commons.poi.excel.annotations.WriteExcel;
import com.hk.emi.core.domain.City;
import lombok.Data;

import java.io.Serializable;

/**
 * 城市导入Vo
 *
 * @author: huangkai
 * @date 2018-04-13 16:34
 */
@Data
public class CityExcelVo implements Serializable {

    /**
     * 上级名称
     */
    @ReadExcel(start = 0)
    private String parentName;

    /**
     * 行政代码
     */
    @ReadExcel(start = 1)
    @WriteExcel(index = 0, value = "行政代码")
    private String code;

    /**
     * 全称
     */
    @ReadExcel(start = 2)
    @WriteExcel(index = 1, value = "全称")
    private String fullName;

    /**
     * 简称
     */
    @ReadExcel(start = 3)
    @WriteExcel(index = 2, value = "简称")
    private String shortName;

    /**
     * 邮编
     */
    @ReadExcel(start = 4)
    @WriteExcel(index = 3, value = "邮政编码")
    private String postOffice;

    /**
     * 城市类型
     * @see City#getCityType()
     */
    @ReadExcel(start = 5)
    private Byte cityType;

    /**
     * 描述
     */
    @ReadExcel(start = 6)
    private String description;
}
