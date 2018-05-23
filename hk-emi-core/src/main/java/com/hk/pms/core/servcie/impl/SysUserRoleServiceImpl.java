package com.hk.pms.core.servcie.impl;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysUserRole;
import com.hk.pms.core.repository.SysUserRoleRepository;
import com.hk.pms.core.servcie.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 17:02
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole,String> implements SysUserRoleService {

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysUserRole, String> getBaseRepository() {
        return sysUserRoleRepository;
    }

    @Override
    public void deleteByUserIdAndRoleId(String userId, String roleId) {
        sysUserRoleRepository.deleteByUserIdAndRoleId(userId,roleId);
    }
}
