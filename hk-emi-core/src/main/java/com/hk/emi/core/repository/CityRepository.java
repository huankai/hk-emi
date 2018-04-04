/**
 *
 */
package com.hk.emi.core.repository;

import com.hk.core.repository.StringRepository;
import com.hk.emi.core.domain.City;

import java.util.List;

/**
 * @author huangkai
 */
public interface CityRepository extends StringRepository<City> {

    /**
     * 查询下级
     *
     * @param parentId
     * @return
     */
    List<City> findByParentId(String parentId);
}
