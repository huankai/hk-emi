package com.hk.pms.core.servcie.impl;

import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysRolePermission;
import com.hk.pms.core.repository.SysRolePermissionRepository;
import com.hk.pms.core.servcie.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: huangkai
 * @date 2018-04-12 17:05
 */
@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission, String> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionRepository sysRolePermissionRepository;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysRolePermission, String> getBaseRepository() {
        return sysRolePermissionRepository;
    }

    @Override
    public void deleteByRoleIdAndPermissionId(String roleId, String permissionId) {
        sysRolePermissionRepository.deleteByRoleIdAndPermissionId(roleId,permissionId);
    }
}
