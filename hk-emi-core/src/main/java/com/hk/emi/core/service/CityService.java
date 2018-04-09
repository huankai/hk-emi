/**
 *
 */
package com.hk.emi.core.service;

import com.hk.core.service.BaseService;
import com.hk.emi.core.domain.City;

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

}
