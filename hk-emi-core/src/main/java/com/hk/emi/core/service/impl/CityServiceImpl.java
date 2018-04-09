/**
 *
 */
package com.hk.emi.core.service.impl;

import com.hk.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.emi.core.domain.City;
import com.hk.emi.core.repository.CityRepository;
import com.hk.emi.core.service.CityService;

import java.util.Collections;
import java.util.List;

/**
 * @author huangkai
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<City, String> implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    protected BaseRepository<City, String> getBaseRepository() {
        return cityRepository;
    }

    @Override
    protected Example<City> getExample(City t) {
        if (null == t) {
            t = new City();
        }
        return Example.of(t, ExampleMatcher.matching()
                .withMatcher("code", GenericPropertyMatchers.exact())
                .withIgnoreNullValues());
    }

    /**
     * 查询下级City
     *
     * @param parentId
     * @return
     */
    @Override
    public List<City> findChildList(String parentId) {
        return StringUtils.isEmpty(parentId) ? Collections.emptyList() : cityRepository.findByParentId(parentId);
    }
}
