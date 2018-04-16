/**
 *
 */
package com.hk.emi.core.service;

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
     * 导入
     *
     * @param in excel文件
     */
    void importExcel(InputStream in);

    /**
     * 根据条件查询生成Excel Byte
     *
     * @param city 
     * @return
     */
    byte[] exportExcelData(City city);
}
