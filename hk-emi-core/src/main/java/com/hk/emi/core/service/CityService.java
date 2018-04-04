/**
 *
 */
package com.hk.emi.core.service;

import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.core.service.BaseService;
import com.hk.emi.core.domain.City;

import java.io.InputStream;
import java.util.List;

/**
 * @author huangkai
 */
public interface CityService extends BaseService<City, String> {

    /**
     * 查询下级City
     *
     * @param parentId
     * @return
     */
    List<City> findChildList(String parentId);

    /**
     * 导入Excel
     *
     * @param excelInput
     * @return
     */
    List<ErrorLog<City>> importExcelData(InputStream excelInput);

    /**
     * 返回导出的Excel byte
     *
     * @param city
     * @return
     */
    byte[] getExcelData(City city);
}
