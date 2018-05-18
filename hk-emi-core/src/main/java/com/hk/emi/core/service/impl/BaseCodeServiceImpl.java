/**
 *
 */
package com.hk.emi.core.service.impl;

import com.hk.core.cache.service.EnableCacheServiceImpl;
import com.hk.core.repository.BaseRepository;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.repository.BaseCodeRepostory;
import com.hk.emi.core.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * @author kally
 * @date 2018年1月24日下午1:46:36
 */
@Service
@CacheConfig(cacheNames = {"BaseCode"})
public class BaseCodeServiceImpl extends EnableCacheServiceImpl<BaseCode, String> implements BaseCodeService {

    @Autowired
    private BaseCodeRepostory baseCodeRepostory;

    @Override
    protected BaseRepository<BaseCode, String> getBaseRepository() {
        return baseCodeRepostory;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }
}
