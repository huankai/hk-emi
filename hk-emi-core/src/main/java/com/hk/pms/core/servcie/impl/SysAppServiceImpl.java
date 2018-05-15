package com.hk.pms.core.servcie.impl;

import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.EnableCacheServiceImpl;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.repository.SysAppRepository;
import com.hk.pms.core.servcie.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 11:32
 */
@Service
@CacheConfig(cacheNames = "SYS_APP")
public class SysAppServiceImpl extends EnableCacheServiceImpl<SysApp, String> implements SysAppService {

    @Autowired
    private SysAppRepository sysAppRepository;

    @Override
    protected BaseRepository<SysApp, String> getBaseRepository() {
        return sysAppRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return ExampleMatcher.matching().withIgnorePaths("appIcon", "appPort");
    }

    /**
     * 根据appCode 查询唯一
     *
     * @param appCode appCode
     * @return
     */
    @Override
    public SysApp findByAppCode(String appCode) {
        AssertUtils.notBlank(appCode);
        return sysAppRepository.findByAppCode(appCode);
    }

    @Override
    public SysApp enable(String id) {
        return updateStatus(id, ByteConstants.ONE);
    }

    @Override
    public SysApp disable(String id) {
        return updateStatus(id, ByteConstants.ZERO);
    }

    private SysApp updateStatus(String appId, Byte status) {
        SysApp app = getOne(appId);
        app.setAppStatus(status);
        return saveOrUpdate(app);
    }
}
