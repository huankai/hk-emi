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
    public <S extends SysPermission> List<S> saveOrUpdate(Iterable<S> entities) {
        entities.forEach(item -> validatePermissionCode(item));
        return super.saveOrUpdate(entities);
    }

    /**
     * @param entity
     * @param <S>
     */
    private <S extends SysPermission> void validatePermissionCode(S entity) {
        AssertUtils.isTrue(StringUtils.notEquals(entity.getPermissionCode(), PermissionContants.PROTECT_ADMIN_PERMISSION), "非法的权限编号");
    }

    @Override
    public <S extends SysPermission> S saveAndFlush(S entity) {
        validatePermissionCode(entity);
        return super.saveAndFlush(entity);
    }

    @Override
    public <S extends SysPermission> S saveOrUpdate(S entity) {
        validatePermissionCode(entity);
        if (null == entity.getParent()) {
            entity.setParent(entity);
        }
        return super.saveOrUpdate(entity);
    }

    @Override
    public List<SysPermission> getPermissionList(String userId, String appId) {
        List<SysRole> roleList = roleService.getRoleList(userId, appId);
        List<SysPermission> permissions = Lists.newArrayList();
        roleList.forEach(role -> permissions.addAll(role.getPermissionSet()));
        return permissions;
    }
}
