package com.hk.pms.core.servcie.impl;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysDeptRole;
import com.hk.pms.core.repository.SysDeptRoleRepository;
import com.hk.pms.core.servcie.SysDeptRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 16:48
 */
@Service
public class SysDeptRoleServiceImpl extends BaseServiceImpl<SysDeptRole, String> implements SysDeptRoleService {

    @Autowired
    private SysDeptRoleRepository sysDeptRoleRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysDeptRole, String> getBaseRepository() {
        return sysDeptRoleRepository;
    }
}
