package com.hk.pms.core.servcie.impl;

import com.hk.commons.util.AssertUtils;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysApp;
import com.hk.pms.core.repository.SysAppRepository;
import com.hk.pms.core.servcie.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 11:32
 */
@Service
public class SysAppServiceImpl extends BaseServiceImpl<SysApp, String> implements SysAppService {

    @Autowired
    private SysAppRepository sysAppRepository;

    @Override
    protected BaseRepository<SysApp, String> getBaseRepository() {
        return sysAppRepository;
    }

    @Override
    protected Example<SysApp> getExample(SysApp t) {
        if(null == t){
            t = new SysApp();
        }
        return Example.of(t, ExampleMatcher.matching().withIgnorePaths("appIcon","appPort"));
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

}
