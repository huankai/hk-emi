package com.hk.pms.core.servcie.impl;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUserThird;
import com.hk.pms.core.repository.SysUserThirdRepository;
import com.hk.pms.core.servcie.SysUserThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 17:03
 */
@Service
public class SysUserThirdServiceImpl extends BaseServiceImpl<SysUserThird, String> implements SysUserThirdService {

    @Autowired
    private SysUserThirdRepository sysUserThirdRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysUserThird, String> getBaseRepository() {
        return sysUserThirdRepository;
    }
}
