package com.hk.pms.core.servcie.impl;

import com.google.common.collect.Lists;
import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.PermissionContants;
import com.hk.core.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.core.domain.SysPermission;
import com.hk.pms.core.domain.SysRole;
import com.hk.pms.core.repository.SysPermissionRepository;
import com.hk.pms.core.servcie.SysPermissionService;
import com.hk.pms.core.servcie.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-04-12 16:53
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String> implements SysPermissionService {

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Autowired
    private SysRoleService roleService;

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysPermission, String> getBaseRepository() {
        return sysPermissionRepository;
    }

    @Override
    protected <S extends SysPermission> S saveBefore(S entity) {
        AssertUtils.isTrue(StringUtils.notEquals(entity.getPermissionCode(), PermissionContants.PROTECT_ADMIN_PERMISSION), "非法的权限编号");
        return super.saveBefore(entity);
    }

    @Override
    public List<SysPermission> getPermissionList(String userId, String appId) {
        List<SysRole> roleList = roleService.getRoleList(userId, appId);
        List<SysPermission> permissions = Lists.newArrayList();
        roleList.forEach(role -> permissions.addAll(role.getPermissionSet()));
        return permissions;
    }
}
