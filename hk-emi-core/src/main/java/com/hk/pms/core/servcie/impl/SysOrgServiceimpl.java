package com.hk.pms.core.servcie.impl;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysOrg;
import com.hk.pms.core.repository.SysOrgRepository;
import com.hk.pms.core.servcie.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 16:52
 */
@Service
public class SysOrgServiceimpl extends BaseServiceImpl<SysOrg, String> implements SysOrgService {

    @Autowired
    private SysOrgRepository sysOrgRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysOrg, String> getBaseRepository() {
        return sysOrgRepository;
    }
}
