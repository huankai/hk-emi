package com.hk.pms.core.servcie.impl;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysRole;
import com.hk.pms.core.repository.SysRoleRepository;
import com.hk.pms.core.servcie.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 16:59
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, String> implements SysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysRole, String> getBaseRepository() {
        return sysRoleRepository;
    }
}
