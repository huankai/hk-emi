/**
 *
 */
package com.hk.emi.core.repository;

import com.hk.core.repository.StringRepository;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.repository.custom.CustomCityRepository;

import java.util.List;

/**
 * @author huangkai
 */
public interface CityRepository extends StringRepository<City>,CustomCityRepository {

    /**
     * 查询下级
     *
     * @param parentId parentId
     * @return
     */
    List<City> findByParentId(String parentId);
}
