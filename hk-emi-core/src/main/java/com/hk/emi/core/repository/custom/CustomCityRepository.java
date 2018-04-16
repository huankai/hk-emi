package com.hk.emi.core.repository.custom;

import com.hk.emi.core.domain.City;
import com.hk.emi.core.vo.CityExcelVo;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-04-16 10:10
 */
public interface CustomCityRepository {

    /**
     * 查询Excel数据
     *
     * @param city
     * @return
     */
    List<CityExcelVo> findExportExcelData(City city);
}
