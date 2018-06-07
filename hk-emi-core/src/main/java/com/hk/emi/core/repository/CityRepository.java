/**
 *
 */
package com.hk.emi.core.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.emi.core.domain.City;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

/**
 * @author huangkai
 */
public interface CityRepository extends StringRepository<City> {

    /**
     * 查询下级
     *
     * @param parentId parentId
     * @return
     */
    List<City> findByParentId(String parentId);


    @Override
    default ExampleMatcher ofExampleMatcher() {
        return ExampleMatcher.matching()
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnoreNullValues();
    }
}
