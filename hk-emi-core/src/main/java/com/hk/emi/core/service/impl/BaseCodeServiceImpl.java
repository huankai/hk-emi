package com.hk.emi.core.service.impl;

import com.hk.core.data.commons.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.emi.core.domain.BaseCode;
import com.hk.emi.core.repository.BaseCodeRepostory;
import com.hk.emi.core.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018年1月24日下午1:46:36
 */
@Service
@CacheConfig(cacheNames = {"BaseCode"})
public class BaseCodeServiceImpl extends BaseServiceImpl<BaseCode, String> implements BaseCodeService {

    @Autowired
    private BaseCodeRepostory baseCodeRepostory;

    @Override
    protected BaseDao<BaseCode, String> getBaseDao() {
        return baseCodeRepostory;
    }
}
