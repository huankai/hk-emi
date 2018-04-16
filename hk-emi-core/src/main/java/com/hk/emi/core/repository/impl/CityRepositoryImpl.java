package com.hk.emi.core.repository.impl;

import com.hk.core.query.jdbc.SelectArguments;
import com.hk.core.repository.impl.BaseDaoImpl;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.repository.custom.CustomCityRepository;
import com.hk.emi.core.vo.CityExcelVo;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-04-16 10:11
 */
public class CityRepositoryImpl extends BaseDaoImpl implements CustomCityRepository {

    /**
     * 查询Excel数据
     *
     * @param city
     * @return
     */
    @Override
    public List<CityExcelVo> findExportExcelData(City city) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sys_city");
        arguments.setFields("code,full_name,short_name,post_office");
        return queryForList(arguments, false, CityExcelVo.class).getResult();
    }
}
